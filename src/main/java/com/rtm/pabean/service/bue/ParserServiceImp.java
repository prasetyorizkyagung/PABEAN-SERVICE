package com.rtm.pabean.service.bue;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.enums.InboxStatusEnum;
import com.rtm.pabean.enums.JobOrderStatusEnum;
import com.rtm.pabean.enums.TrackingStatusEnum;
import com.rtm.pabean.model.bue.Inbox;
import com.rtm.pabean.model.bue.PebHeader;
import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.order.JobOrder;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.model.tracking.StatusDetail;
import com.rtm.pabean.service.ParserEngine;
import com.rtm.pabean.service.QueueService;
import com.rtm.pabean.service.module.BueParser;
import com.rtm.pabean.service.order.JobOrderService;
import com.rtm.pabean.service.tracking.StatusDetailService;
import com.rtm.pabean.service.tracking.TrackingService;
import com.rtm.pabean.utils.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("bueParserServiceImp")
public class ParserServiceImp extends ParserEngine<Inbox> {
    
    private final String ORDER_NOT_EXISTS = "Nomor Order[%s] tidak ditemukan";

    private final String CAR_NOT_EXISTS = "Nomor Aju[%s] tidak ditemukan";

    @Autowired
    private ApplicationContext applicationContext;

    private InboxService inboxService;

    private TrackingService trackingService;

    private StatusDetailService statusDetailService;

    private JobOrderService jobOrderService;

    public ParserServiceImp(
        @Qualifier("bueQueueInboxServiceImp") QueueService<Inbox> queueService, 
        InboxService inboxService, 
        TrackingService trackingService, 
        StatusDetailService statusDetailService,
        JobOrderService jobOrderService
    ) {
        super(queueService);
        this.inboxService = inboxService;
        this.trackingService = trackingService;
        this.statusDetailService = statusDetailService;
        this.jobOrderService = jobOrderService;
    }

    @Override
    @Transactional
    public void parseFile(Inbox data) {
        BueParser bueParser = (BueParser) applicationContext.getBean("bueParser");
        try {
            inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.HANDLED.getCode(), null);

            bueParser.setSourceFile(data.getFilePath());
            TblPebHdr tblPebHdr = bueParser.getTblPebHdr();

            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(data.getOrderId());
            if (!opDocumentTracking.isPresent()) {
                data.setStatus(InboxStatusEnum.ERROR.getCode());
                data.setMessage(String.format(ORDER_NOT_EXISTS, data.getOrderId()));
                inboxService.saveOrUpdate(data);
                throw new Exception(String.format(ORDER_NOT_EXISTS, data.getOrderId()));
            }

            DocumentTracking documentTracking = opDocumentTracking.get();
            if (!Arrays.asList(
                TrackingStatusEnum.QUEUE.getCode(), 
                TrackingStatusEnum.DRAFT_PENGAJUAN.getCode(), 
                TrackingStatusEnum.PENGAJUAN_REVISI.getCode(),
                TrackingStatusEnum.REJECT.getCode()).contains(documentTracking.getStatusCode())) {
                    data.setStatus(InboxStatusEnum.ERROR.getCode());
                    data.setMessage(String.format(CAR_NOT_EXISTS, documentTracking.getCar()));
                    inboxService.saveOrUpdate(data);
                    throw new Exception(String.format(CAR_NOT_EXISTS, documentTracking.getCar()));
            }

            Optional<PebHeader> opPebHeader = bueParser.getPebHeader(data.getOrderId());
            if (opPebHeader.isPresent()) {
                bueParser.deleteDocument(opPebHeader.get().getPebId());
                bueParser.deleteContainer(opPebHeader.get().getPebId());
                bueParser.deleteBankDhe(opPebHeader.get().getPebId());
                bueParser.deleteTransport(opPebHeader.get().getPebId());
                bueParser.deletePackage(opPebHeader.get().getPebId());
                bueParser.deleteItemDetail(opPebHeader.get().getPebId());
                bueParser.deletePebHeader(opPebHeader.get().getPebId());
            }

            PebHeader pebHeader = bueParser.savePebHeader(data.getOrderId());
            bueParser.saveItemDetail(pebHeader.getPebId());
            bueParser.savePackage(pebHeader.getPebId());
            bueParser.saveTransport(pebHeader.getPebId());
            bueParser.saveBankDhe(pebHeader.getPebId());
            bueParser.saveContainer(pebHeader.getPebId());
            bueParser.saveDocument(pebHeader.getPebId());

            documentTracking.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
            trackingService.saveOrUpdate(documentTracking);

            StatusDetail statusDetail = new StatusDetail();
            statusDetail.setCar(tblPebHdr.getCar());
            statusDetail.setTrackingId(documentTracking.getTrackingId());
            statusDetail.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
            statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
            statusDetail.setStatusDatetime(new Date());
            statusDetail.setDescription("DRAFT PENGAJUAN");
            statusDetail.setCreatedBy("SYS");
            statusDetail.setCreatedDate(new Date());
            statusDetailService.saveOrUpdate(statusDetail);

            String dateCheck = DateUtil.toString(tblPebHdr.getTgSiap(), DateUtil.YYYYMMDD);
            String timeCheck = "000000";
            if (tblPebHdr.getWkSiap() != null) {
                timeCheck = String.format("%-6s", tblPebHdr.getWkSiap());
            }
            Date dateTimeCheck = DateUtil.toDate(dateCheck + timeCheck, DateUtil.YYYYMMDDHHMMSS);
            bueParser.saveItemReadiness(pebHeader.getPebId(), dateTimeCheck);
            inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.DONE.getCode(), "Success");

            Optional<JobOrder> opJobOrder = jobOrderService.findByOrderId(data.getOrderId());
            if (opJobOrder.isPresent()) {
                jobOrderService.updateStatus(data.getOrderId(), JobOrderStatusEnum.SUC_PARSING.getCode(), null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                data.setStatus(InboxStatusEnum.ERROR.getCode());
                data.setMessage(e.getLocalizedMessage());
                inboxService.saveOrUpdate(data);

                Optional<JobOrder> opJobOrder = jobOrderService.findByOrderId(data.getOrderId());
                if (opJobOrder.isPresent()) {
                    jobOrderService.updateStatus(data.getOrderId(), JobOrderStatusEnum.ERR_PARSING.getCode(), e.getLocalizedMessage());
                }
            } catch (Exception e1) {
                log.error(e.getLocalizedMessage(), e);
            }
        }
        /* try {
            inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.HANDLED.getCode(), null);
            bueParser.setSourceFile(data.getFilePath());
            TblPebHdr tblPebHdr = bueParser.getTblPebHdr();
            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(data.getOrderId());
            // Optional<DocumentTracking> opDocumentTracking = trackingService.findDocByCar(tblPebHdr.getCar(), DocumentTypeEnum.BC30.getCode());
            // if (opDocumentTracking.isPresent() && 
                    // Arrays.asList(TrackingStatusEnum.QUEUE.getCode(), TrackingStatusEnum.DRAFT_PENGAJUAN.getCode(), TrackingStatusEnum.REJECT.getCode()).contains(opDocumentTracking.get().getStatusCode())) {
            if (opDocumentTracking.isPresent()) {
                PebHeader pebHeader = bueParser.savePebHeader();
                bueParser.saveItemDetail(pebHeader.getPebId());
                bueParser.savePackage(pebHeader.getPebId());
                bueParser.saveTransport(pebHeader.getPebId());
                bueParser.saveBankDhe(pebHeader.getPebId());
                bueParser.saveContainer(pebHeader.getPebId());
                bueParser.saveDocument(pebHeader.getPebId());

                DocumentTracking documentTracking = opDocumentTracking.get();
                documentTracking.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
                trackingService.saveOrUpdate(documentTracking);

                StatusDetail statusDetail = new StatusDetail();
                statusDetail.setCar(tblPebHdr.getCar());
                statusDetail.setTrackingId(documentTracking.getTrackingId());
                statusDetail.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
                statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
                statusDetail.setStatusDatetime(new Date());
                statusDetail.setDescription("DRAFT PENGAJUAN");
                statusDetail.setCreatedBy("SYS");
                statusDetail.setCreatedDate(new Date());
                statusDetailService.saveOrUpdate(statusDetail);

                String dateCheck = DateUtil.toString(tblPebHdr.getTgSiap(), DateUtil.YYYYMMDD);
                String timeCheck = "000000";
                if (tblPebHdr.getWkSiap() != null) {
                    timeCheck = String.format("%-6s", tblPebHdr.getWkSiap());
                }
                Date dateTimeCheck = DateUtil.toDate(dateCheck + timeCheck, DateUtil.YYYYMMDDHHMMSS);
                bueParser.saveItemReadiness(pebHeader.getPebId(), dateTimeCheck);
                inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.DONE.getCode(), timeCheck);

                Optional<JobOrder> opJobOrder = jobOrderService.findByOrderId(data.getOrderId());
                if (opJobOrder.isPresent()) {
                    jobOrderService.updateStatus(data.getOrderId(), JobOrderStatusEnum.SUC_PARSING.getCode(), null);
                }
            } else {
                data.setMessage("Aju tidak ditemukan dengan nomor order ["+ data.getOrderId() +"]");
                // data.setMessage("Nomor aju sudah dipakai ["+ tblPebHdr.getCar() +"] dengan status ["+ opDocumentTracking.get().getStatusCode() +"]");
                data.setStatus(InboxStatusEnum.ERROR.getCode());
                inboxService.saveOrUpdate(data);
                
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            try {
                Optional<JobOrder> opJobOrder = jobOrderService.findByOrderId(data.getOrderId());
                if (opJobOrder.isPresent()) {
                    jobOrderService.updateStatus(data.getOrderId(), JobOrderStatusEnum.ERR_PARSING.getCode(), null);
                }
            } catch (Exception e1) {
                log.error(e.getLocalizedMessage(), e);
            }
        } */
    }
    
}

package com.rtm.pabean.service.bup;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.enums.InboxStatusEnum;
import com.rtm.pabean.enums.JobOrderStatusEnum;
import com.rtm.pabean.enums.TrackingStatusEnum;
import com.rtm.pabean.model.bup.Document;
import com.rtm.pabean.model.bup.Inbox;
import com.rtm.pabean.model.bup.PibHeader;
import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.model.order.JobOrder;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.model.tracking.StatusDetail;
import com.rtm.pabean.service.ParserEngine;
import com.rtm.pabean.service.QueueService;
import com.rtm.pabean.service.module.BupParser;
import com.rtm.pabean.service.order.JobOrderService;
import com.rtm.pabean.service.tracking.StatusDetailService;
import com.rtm.pabean.service.tracking.TrackingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("bupParserServiceImp")
public class ParserServiceImp extends ParserEngine<Inbox> {
    
    private final String ORDER_NOT_EXISTS = "Nomor Order[%s] tidak ditemukan";

    private final String CAR_NOT_EXISTS = "Nomor Aju[%s] tidak ditemukan";

    @Autowired
    private ApplicationContext applicationContext;

    private InboxService inboxService;

    private TrackingService trackingService;

    private StatusDetailService statusDetailService;

    private JobOrderService jobOrderService;

    @Autowired
    public ParserServiceImp(
        @Qualifier("bupQueueInboxServiceImp")QueueService<Inbox> queueService, 
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
        BupParser bupParser = (BupParser) applicationContext.getBean("bupParser");
        try {
            inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.HANDLED.getCode(), null);

            bupParser.setSourceFile(data.getFilePath());
            TblPibHdr tblPibHdr = bupParser.getTblPibHdr();

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

            Optional<PibHeader> opPibHeader = bupParser.getPibHeader(data.getOrderId());
            if (opPibHeader.isPresent()) {
                bupParser.deleteItemDetail(opPibHeader.get().getPibId());
                bupParser.deleteDocument(opPibHeader.get().getPibId());
                bupParser.deleteEntity(opPibHeader.get().getPibId());
                bupParser.deletePackage(opPibHeader.get().getPibId());
                bupParser.deleteTransport(opPibHeader.get().getPibId());
                bupParser.deleteContainer(opPibHeader.get().getPibId());
                bupParser.deletePibHeader(opPibHeader.get().getPibId());
            }

            PibHeader pibHeader = bupParser.savePibHeader(data.getOrderId());
            bupParser.saveContainer(pibHeader.getPibId());
            List<Document> documents = bupParser.saveDocument(pibHeader.getPibId());
            bupParser.saveItemDetail(documents, pibHeader);
            bupParser.savePackage(pibHeader.getPibId());
            bupParser.saveTransport(pibHeader.getPibId());
            bupParser.saveEntity(pibHeader.getPibId());

            documentTracking.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
            trackingService.saveOrUpdate(documentTracking);

            StatusDetail statusDetail = new StatusDetail();
            statusDetail.setCar(tblPibHdr.getCar());
            statusDetail.setTrackingId(documentTracking.getTrackingId());
            statusDetail.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
            statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
            statusDetail.setStatusDatetime(new Date());
            statusDetail.setDescription("DRAFT PENGAJUAN");
            statusDetail.setCreatedBy("SYS");
            statusDetail.setCreatedDate(new Date());
            statusDetailService.saveOrUpdate(statusDetail);

            inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.DONE.getCode(), null);

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
            bupParser.setSourceFile(data.getFilePath());
            TblPibHdr tblPibHdr = bupParser.getTblPibHdr();
            Optional<DocumentTracking> opDocumentTracking = trackingService.findCarByOrder(data.getOrderId(), DocumentTypeEnum.BC20.getCode());
            // Optional<DocumentTracking> opDocumentTracking = trackingService.findDocByCar(tblPibHdr.getCar(), DocumentTypeEnum.BC20.getCode());
            if (opDocumentTracking.isPresent() && 
                    Arrays.asList(TrackingStatusEnum.QUEUE.getCode(), TrackingStatusEnum.DRAFT_PENGAJUAN.getCode(), TrackingStatusEnum.REJECT.getCode()).contains(opDocumentTracking.get().getStatusCode())) {

                PibHeader pibHeader = bupParser.savePibHeader(data.getOrderId());
                bupParser.saveContainer(pibHeader.getPibId());
                List<Document> documents = bupParser.saveDocument(pibHeader.getPibId());
                bupParser.saveItemDetail(documents, pibHeader);
                bupParser.savePackage(pibHeader.getPibId());
                bupParser.saveTransport(pibHeader.getPibId());
                bupParser.saveEntity(pibHeader.getPibId());

                DocumentTracking documentTracking = opDocumentTracking.get();
                documentTracking.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
                trackingService.saveOrUpdate(documentTracking);

                StatusDetail statusDetail = new StatusDetail();
                statusDetail.setCar(tblPibHdr.getCar());
                statusDetail.setTrackingId(documentTracking.getTrackingId());
                statusDetail.setStatusCode(TrackingStatusEnum.DRAFT_PENGAJUAN.getCode());
                statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
                statusDetail.setStatusDatetime(new Date());
                statusDetail.setDescription(TrackingStatusEnum.DRAFT_PENGAJUAN.name());
                statusDetail.setCreatedBy("SYS");
                statusDetail.setCreatedDate(new Date());
                statusDetailService.saveOrUpdate(statusDetail);

                inboxService.updateStatus(data.getInboxId(), InboxStatusEnum.DONE.getCode(), null);

                Optional<JobOrder> opJobOrder = jobOrderService.findByOrderId(data.getOrderId());
                if (opJobOrder.isPresent()) {
                    jobOrderService.updateStatus(data.getOrderId(), JobOrderStatusEnum.SUC_PARSING.getCode(), null);
                }
            } else {
                data.setMessage("Aju tidak ditemukan dengan nomor order ["+ data.getOrderId() +"]");
                // data.setMessage("Nomor aju sudah dipakai ["+ tblPibHdr.getCar() +"] dengan status ["+ opDocumentTracking.get().getStatusCode() +"]");
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

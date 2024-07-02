package com.rtm.pabean.service.bup;

import java.io.File;
import java.util.Date;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dto.ceisa.Queue;
import com.rtm.pabean.dto.ceisa.ResponseCeisa;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;
import com.rtm.pabean.enums.OutboxStatusEnum;
import com.rtm.pabean.enums.TrackingStatusEnum;
import com.rtm.pabean.model.bup.Outbox;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.model.tracking.StatusDetail;
import com.rtm.pabean.service.QueueService;
import com.rtm.pabean.service.SubmitService;
import com.rtm.pabean.service.ceisa.CeisaService;
import com.rtm.pabean.service.ceisa.PibGenerator;
import com.rtm.pabean.service.tracking.StatusDetailService;
import com.rtm.pabean.service.tracking.TrackingService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class BupServiceImp implements BupService {

    @Value("${rtm.pabean.folder.bup}")
    private String bupFolder;

    private TrackingService trackingService;

    private OutboxService outboxService;

    // private PibGenerator pibGenerator;

    private StatusDetailService statusDetailService;

    private CeisaService ceisaService;

    private QueueService<Outbox> queueService;

    private SubmitService<Outbox> submitService;

    private final String CAR_NOT_EXISTS = "Nomor Aju[%s] tidak ditemukan";

    @Autowired
    public BupServiceImp(
        TrackingService trackingService, 
        OutboxService outboxService, 
        // PibGenerator pibGenerator, 
        StatusDetailService statusDetailService, 
        CeisaService ceisaService,
        @Qualifier("bupQueueOutboxServiceImp")
        QueueService<Outbox> queueService,
        @Qualifier("bupSubmitServiceImp")
        SubmitService<Outbox> submitService
    ) {
        this.trackingService = trackingService;
        this.outboxService = outboxService;
        // this.pibGenerator = pibGenerator;
        this.statusDetailService = statusDetailService;
        this.ceisaService = ceisaService;
        this.queueService = queueService;
        this.submitService = submitService;
    }

    @Override
    @Transactional
    public String incomingDocument(File file, String orderId, String companyId) throws Exception {
        return null;
    }

    @Override
    public Outbox queueCeisa(Queue queue) throws Exception {
        try {
            if(StringUtils.isEmpty(queue.getUserName()))
                throw new Exception("username cannot be empty");

            if(StringUtils.isEmpty(queue.getPassword()))
                throw new Exception("Password cannot be empty");

            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(queue.getOrderId());
            if (!opDocumentTracking.isPresent()) {
                throw new Exception(String.format(CAR_NOT_EXISTS, queue.getCar()));
            } else {
                DocumentTracking documentTracking = opDocumentTracking.get();
                documentTracking.setStatusCode(TrackingStatusEnum.SENT_CEISA.getCode());
                trackingService.saveOrUpdate(documentTracking);

                StatusDetail statusDetail = new StatusDetail();
                statusDetail.setCar(queue.getCar());
                statusDetail.setTrackingId(documentTracking.getTrackingId());
                statusDetail.setStatusCode(TrackingStatusEnum.SENT_CEISA.getCode());
                statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
                statusDetail.setStatusDatetime(new Date());
                statusDetail.setDescription(TrackingStatusEnum.SENT_CEISA.name());
                statusDetail.setCreatedBy("SYS");
                statusDetail.setCreatedDate(new Date());
                statusDetailService.saveOrUpdate(statusDetail);

                Outbox outbox = new Outbox();

                ResponseCeisa responseLogin = ceisaService.login(queue.getUserName(), queue.getPassword());
                if (responseLogin == null) {
                    documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                    trackingService.saveOrUpdate(documentTracking);

                    StatusDetail newStatusDetail = new StatusDetail();
                    newStatusDetail.setCar(queue.getCar());
                    newStatusDetail.setTrackingId(documentTracking.getTrackingId());
                    newStatusDetail.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                    newStatusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
                    newStatusDetail.setStatusDatetime(new Date());
                    newStatusDetail.setDescription("GAGAL MASUK CEISA, PASTIKAN USERNAME ATAU PASSWORD BENAR");
                    newStatusDetail.setCreatedBy("SYS");
                    newStatusDetail.setCreatedDate(new Date());
                    statusDetailService.saveOrUpdate(newStatusDetail);

                    throw new Exception("GAGAL MASUK CEISA, PASTIKAN USERNAME ATAU PASSWORD BENAR");
                } else {
                    outbox.setCar(queue.getCar());
                    outbox.setOrderId(queue.getOrderId());
                    outbox.setStatus(OutboxStatusEnum.QUEUE.getCode());
                    outbox.setUserName(queue.getUserName());
                    outbox.setPassword(queue.getPassword());
                    outbox.setCreatedDate(new Date());
                    outbox.setFinal_(false);
                    outboxService.saveOrUpdate(outbox);

                    queueService.addQueue(outbox);

                    submitService.setPaused(false);
                }

                return outbox;
            }
        } catch (Exception e) {
            log.error("Error queue ceisa", e);
            throw new Exception(e.getLocalizedMessage());
        }
    }

    @Override
    public Pib generatePib(String car) {
        return null;
        // return pibGenerator.generateData(car);
    }
    
}

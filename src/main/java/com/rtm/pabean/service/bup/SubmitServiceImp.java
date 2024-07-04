package com.rtm.pabean.service.bup;

import java.util.Date;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.bup.PibHeaderDao;
import com.rtm.pabean.dto.ceisa.ResponseCeisa;
import com.rtm.pabean.dto.ceisa.document.bup.Pib;
import com.rtm.pabean.enums.DocumentTypeEnum;
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
import com.rtm.pabean.utils.JsonUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("bupSubmitServiceImp")
public class SubmitServiceImp extends SubmitService<Outbox>  {

    private OutboxService outboxService;

    private TrackingService trackingService;

    private StatusDetailService statusDetailService;

    private CeisaService ceisaService;

    // private BupService bupService;
    
    private PibGenerator pibGenerator;

    @Autowired
    public SubmitServiceImp(@Qualifier("bupQueueOutboxServiceImp") QueueService<Outbox> queueService, OutboxService outboxService, TrackingService trackingService, StatusDetailService statusDetailService, CeisaService ceisaService, PibGenerator pibGenerator) {
        super(queueService);
        this.outboxService = outboxService;
        this.trackingService = trackingService;
        this.statusDetailService = statusDetailService;
        this.ceisaService = ceisaService;
        this.pibGenerator = pibGenerator;
    }

    @Autowired
    private PibHeaderDao pibHeaderDao;

    @Override
    @Transactional
    public void queueSubmit(Outbox data) {
        try {
            outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.HANDLED.getCode(), null);

            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(data.getOrderId());
            DocumentTracking documentTracking = null;
            if (opDocumentTracking.isPresent()) {
                documentTracking = opDocumentTracking.get();

                StatusDetail statusDetail = new StatusDetail();
                statusDetail.setCar(data.getCar());
                statusDetail.setTrackingId(documentTracking.getTrackingId());
                statusDetail.setDocumentCode(DocumentTypeEnum.BC20.getCode());
                statusDetail.setStatusDatetime(new Date());
                statusDetail.setCreatedBy("SYS");
                statusDetail.setCreatedDate(new Date());

                Pib pib = pibGenerator.generateData(data.getCar());

                log.info("PIB :: " + pib.toString());

                ResponseCeisa responseLogin = ceisaService.login(data.getUserName(), data.getPassword());
                if (responseLogin != null) {
                    String jsonResponseLogin = JsonUtil.createJsonString(responseLogin);
                    if ("success".equals(responseLogin.getStatus())) {
                        
                        String responseSubmit = ceisaService.submitPib(data.getCar(), responseLogin.getItem().getAccessToken(), false, pib);
                        Map<String, Object> responseMap = JsonUtil.jsonStringToMap(responseSubmit);
                        if (responseMap != null) {
                            String message = responseMap.get("message").toString().toUpperCase();
                            if ("OK".equals(responseMap.get("status").toString())) {

                                statusDetail.setDescription("BERHASIL MASUK CEISA");
                                statusDetail.setStatusCode(TrackingStatusEnum.SUCCESS_CEISA.getCode());

                                outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.DONE.getCode(), message);

                                pibHeaderDao.updateIdHeaderByCar(responseMap.get("idHeader").toString(), data.getCar());

                                documentTracking.setStatusCode(TrackingStatusEnum.SUCCESS_CEISA.getCode());
                                trackingService.saveOrUpdate(documentTracking);
                            } else {
                                statusDetail.setDescription("GAGAL MASUK CEISA, [" + message + "]");
                                statusDetail.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());

                                outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.ERROR.getCode(), message);

                                documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                                trackingService.saveOrUpdate(documentTracking);
                            }
                        } else {
                            statusDetail.setDescription("GAGAL MASUK CEISA, KONEKSI TERPUTUS");
                            statusDetail.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());

                            outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.ERROR.getCode(), responseSubmit);

                            documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                            trackingService.saveOrUpdate(documentTracking);
                        }
                    } else {
                        statusDetail.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                        statusDetail.setDescription("GAGAL MASUK CEISA, AUTENTIKASI BERMASALAH");

                        outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.ERROR.getCode(), jsonResponseLogin);

                        documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                        trackingService.saveOrUpdate(documentTracking);
                    }
                } else {
                    statusDetail.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                    statusDetail.setDescription("GAGAL MASUK CEISA, PASTIKAN USERNAME ATAU PASSWORD BENAR");

                    outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.ERROR.getCode(), "LOGIN CEISA ERROR RESPONSE NULL");

                    documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                    trackingService.saveOrUpdate(documentTracking);
                }
                statusDetailService.saveOrUpdate(statusDetail);
            } else {
                throw new Exception(String.format("Document tracking not found with car [%s]", data.getCar()));
            }
        } catch (Exception e) {
            log.error(e.getLocalizedMessage(), e);
            try {
                outboxService.updateStatus(data.getOutboxId(), OutboxStatusEnum.ERROR.getCode(), e.getLocalizedMessage());

                Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(data.getOrderId());
                DocumentTracking documentTracking = null;
                if (opDocumentTracking.isPresent()) {
                    documentTracking = opDocumentTracking.get();
                    documentTracking.setStatusCode(TrackingStatusEnum.ERROR_CEISA.getCode());
                    trackingService.saveOrUpdate(documentTracking);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
    
}

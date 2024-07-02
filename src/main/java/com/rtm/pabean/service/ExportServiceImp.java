package com.rtm.pabean.service;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.enums.DocumentTypeEnum;
import com.rtm.pabean.enums.InboxStatusEnum;
import com.rtm.pabean.enums.TrackingStatusEnum;
import com.rtm.pabean.model.bue.Inbox;
import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.model.tracking.StatusDetail;
import com.rtm.pabean.service.bue.InboxService;
import com.rtm.pabean.service.module.BueReader;
import com.rtm.pabean.service.tracking.StatusDetailService;
import com.rtm.pabean.service.tracking.TrackingService;
import com.rtm.pabean.utils.FileUtil;

@Service
public class ExportServiceImp implements ExportService {

    private final String CAR_EXISTS = "Nomor Aju sudah ada dengan status %s";

    private final String CAR_EXISTS_OTHER_COMPANY = "Nomor Aju sudah dipakai perusahaan lain";

    private final String CAR_CANNOT_UPDATE = "Nomor Aju [%s] tidak dapat diubah";

    @Value("${rtm.pabean.folder.bue}")
    private String bueFolder;

    private TrackingService trackingService;

    private StatusDetailService statusDetailService;

    private InboxService inboxService;

    private BueReader bueReader;

    private QueueService<Inbox> queueService;

    private ParserEngine<Inbox> parserEngine;

    @Autowired
    public ExportServiceImp(
        TrackingService trackingService, 
        StatusDetailService statusDetailService,
        InboxService inboxService,
        BueReader bueReader,
        @Qualifier("bueQueueInboxServiceImp")
        QueueService<Inbox> queueService,
        @Qualifier("bueParserServiceImp") 
        ParserEngine<Inbox> parserEngine
    ) {
        this.trackingService = trackingService;
        this.statusDetailService = statusDetailService;
        this.inboxService = inboxService;
        this.bueReader = bueReader;
        this.queueService = queueService;
        this.parserEngine = parserEngine;
    }

    @Override
    @Transactional
    public TblPebHdr store(String orderId, String companyId, File file) throws Exception {
        bueReader.setSourceFile(file.getAbsolutePath());
        TblPebHdr tblPebHdr = bueReader.getTblPebHdr();

        Optional<DocumentTracking> opDocumentTracking = trackingService.getByCar(tblPebHdr.getCar());
        DocumentTracking documentTracking = null;
        if (opDocumentTracking.isPresent()) {
            documentTracking = opDocumentTracking.get();
            if (StringUtils.isNotBlank(companyId) && !companyId.equals(documentTracking.getCompanyId())) {
                throw new Exception(CAR_EXISTS_OTHER_COMPANY);
            }
            if (TrackingStatusEnum.QUEUE.getCode().equalsIgnoreCase(documentTracking.getStatusCode())) {
                throw new Exception(String.format(CAR_EXISTS, TrackingStatusEnum.QUEUE.name()));
            }
        }

        documentTracking = new DocumentTracking();
        documentTracking.setCar(tblPebHdr.getCar());
        documentTracking.setCompanyId(companyId);
        documentTracking.setCustomsOfficeCode(tblPebHdr.getKdKtr());
        documentTracking.setDocumentTypeCode(DocumentTypeEnum.BC30.getCode());
        documentTracking.setStatusCode(TrackingStatusEnum.QUEUE.getCode());
        documentTracking.setOrderId(orderId);
        trackingService.saveOrUpdate(documentTracking);

        StatusDetail statusDetail = new StatusDetail();
        statusDetail.setCar(tblPebHdr.getCar());
        statusDetail.setTrackingId(documentTracking.getTrackingId());
        statusDetail.setStatusCode(TrackingStatusEnum.QUEUE.getCode());
        statusDetail.setDocumentCode(DocumentTypeEnum.BC30.getCode());
        statusDetail.setStatusDatetime(new Date());
        statusDetail.setDescription("PEMBUATAN DOKUMEN");
        statusDetail.setCreatedBy("SYS");
        statusDetail.setCreatedDate(new Date());
        statusDetailService.saveOrUpdate(statusDetail);

        String targetPath = FileUtil.generateDirDate(bueFolder) + "/" + file.getName();
        Files.move(Paths.get(file.getAbsolutePath()), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

        Inbox inbox = new Inbox();
        inbox.setFilePath(targetPath);
        inbox.setCompanyId(companyId);
        inbox.setStatus(InboxStatusEnum.QUEUE.getCode());
        inbox.setOrderId(orderId);
        inboxService.saveOrUpdate(inbox);

        // QUEUE PROCESS
        queueService.addQueue(inbox);

        parserEngine.setPaused(false);
    
        return tblPebHdr;
    }

    @Override
    public TblPebHdr update(DocumentTracking documentTracking, File file) throws Exception {
        if (!Arrays.asList(
            TrackingStatusEnum.QUEUE.getCode(), 
            TrackingStatusEnum.DRAFT_PENGAJUAN.getCode(), 
            TrackingStatusEnum.PENGAJUAN_REVISI.getCode(),
            TrackingStatusEnum.REJECT.getCode(),
            TrackingStatusEnum.BATAL_PENGAJUAN.getCode()).contains(documentTracking.getStatusCode())) {
            throw new Exception(String.format(CAR_CANNOT_UPDATE, documentTracking.getCar()));
        }

        bueReader.setSourceFile(file.getAbsolutePath());
        TblPebHdr tblPebHdr = bueReader.getTblPebHdr();

        documentTracking.setCar(tblPebHdr.getCar());
        documentTracking.setCustomsOfficeCode(tblPebHdr.getKdKtr());
        documentTracking.setDocumentTypeCode(DocumentTypeEnum.BC30.getCode());
        documentTracking.setStatusCode(TrackingStatusEnum.PENGAJUAN_REVISI.getCode());
        trackingService.saveOrUpdate(documentTracking);

        StatusDetail statusDetail = new StatusDetail();
        statusDetail.setCar(tblPebHdr.getCar());
        statusDetail.setTrackingId(documentTracking.getTrackingId());
        statusDetail.setStatusCode(TrackingStatusEnum.PENGAJUAN_REVISI.getCode());
        statusDetail.setDocumentCode(DocumentTypeEnum.BC30.getCode());
        statusDetail.setStatusDatetime(new Date());
        statusDetail.setDescription("PENGAJUAN REVISI");
        statusDetail.setCreatedBy("SYS");
        statusDetail.setCreatedDate(new Date());
        statusDetailService.saveOrUpdate(statusDetail);

        String targetPath = FileUtil.generateDirDate(bueFolder) + "/" + file.getName();
        Files.move(Paths.get(file.getAbsolutePath()), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);

        Inbox inbox = new Inbox();
        inbox.setFilePath(targetPath);
        inbox.setCompanyId(documentTracking.getCompanyId());
        inbox.setStatus(InboxStatusEnum.QUEUE.getCode());
        inbox.setOrderId(documentTracking.getOrderId());
        inboxService.saveOrUpdate(inbox);

        // QUEUE PROCESS
        queueService.addQueue(inbox);

        parserEngine.setPaused(false);

        return tblPebHdr;
    }
    
}

package com.rtm.pabean.service;

import java.io.File;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.service.tracking.TrackingService;
import com.rtm.pabean.utils.FileUtil;

@Service
public class PabeanServiceImp implements PabeanService {

    private final String ORDER_EXISTS = "Nomor Order[%s] sudah ada";

    private final String ORDER_NOT_EXISTS = "Nomor Order[%s] tidak ditemukan";

    private final String SUCCESS_ADD_QUEUE = "Document dalam proses parsing, tunggu hingga status DRAFT nomor aju [%s]";

    private TrackingService trackingService;

    private ExportService exportService;

    private ImportService importService;

    @Autowired
    public PabeanServiceImp(
        TrackingService trackingService, 
        ExportService exportService,
        ImportService importService
    ) {
        this.trackingService = trackingService;
        this.exportService = exportService;
        this.importService = importService;
    }

    @Override
    @Transactional
    public String incomingDocument(String documentType, String orderId, String companyId, File file) throws Exception {
        try {
            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(orderId);
            if (opDocumentTracking.isPresent()) {
                throw new Exception(String.format(ORDER_EXISTS, opDocumentTracking.get().getOrderId()));
            }

            String car = StringUtils.EMPTY;
            if (documentType.equalsIgnoreCase("BUP")) {
                TblPibHdr tblPibHdr = importService.store(orderId, companyId, file);
                car = tblPibHdr.getCar();
            } else {
                TblPebHdr tblPebHdr = exportService.store(orderId, companyId, file);
                car = tblPebHdr.getCar();
            }

            FileUtil.deleteFile(file.getAbsolutePath());
            return String.format(SUCCESS_ADD_QUEUE, car);
        } catch (Exception e) {
            e.printStackTrace();
            FileUtil.deleteFile(file.getAbsolutePath());
            throw new Exception(e.getLocalizedMessage());
        }
    }

    @Override
    @Transactional
    public String updatedDocument(String documentType, String orderId, String companyId, File file) throws Exception {
        try {
            Optional<DocumentTracking> opDocumentTracking = trackingService.getByOrderId(orderId);
            if (!opDocumentTracking.isPresent()) {
                throw new Exception(String.format(ORDER_NOT_EXISTS, orderId));
            }
            
            String car = StringUtils.EMPTY;
            DocumentTracking documentTracking = opDocumentTracking.get();
            if (documentType.equalsIgnoreCase("BUP")) {
                TblPibHdr tblPibHdr = importService.update(documentTracking, file);
                car = tblPibHdr.getCar();
            } else {
                TblPebHdr tblPebHdr = exportService.update(documentTracking, file);
                car = tblPebHdr.getCar();
            }

            FileUtil.deleteFile(file.getAbsolutePath());
            return String.format(SUCCESS_ADD_QUEUE, car);
        } catch (Exception e) {
            e.printStackTrace();
            FileUtil.deleteFile(file.getAbsolutePath());
            throw new Exception(e.getLocalizedMessage());
        }
    }
    
}

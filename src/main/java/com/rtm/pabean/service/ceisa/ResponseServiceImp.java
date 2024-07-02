package com.rtm.pabean.service.ceisa;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dto.ceisa.ResponseCeisa;
import com.rtm.pabean.dto.ceisa.ResponseDetail;
import com.rtm.pabean.model.tracking.DocumentTracking;
import com.rtm.pabean.model.tracking.MstResponseDocument;
import com.rtm.pabean.model.tracking.ResponseDetailPK;
import com.rtm.pabean.model.tracking.ResponseHeader;
import com.rtm.pabean.model.tracking.StatusDetail;
import com.rtm.pabean.service.tracking.ResponseDetailService;
import com.rtm.pabean.service.tracking.ResponseDocumentService;
import com.rtm.pabean.service.tracking.ResponseHeaderService;
import com.rtm.pabean.service.tracking.StatusDetailService;
import com.rtm.pabean.service.tracking.TrackingService;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.FileUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ResponseServiceImp implements ResponseService {

    @Value("${rtm.pabean.folder.response}")
    private String responseFolder;

    private CeisaService ceisaService;

    private TrackingService trackingService;

    private StatusDetailService statusDetailService;

    private ResponseHeaderService responseHeaderService;

    private ResponseDocumentService responseDocumentService;

    private ResponseDetailService responseDetailService;

    @Autowired
    public ResponseServiceImp(CeisaService ceisaService, TrackingService trackingService, StatusDetailService statusDetailService, ResponseHeaderService responseHeaderService, ResponseDocumentService responseDocumentService, ResponseDetailService responseDetailService) {
        this.ceisaService = ceisaService;
        this.trackingService = trackingService;
        this.statusDetailService = statusDetailService;
        this.responseHeaderService = responseHeaderService;
        this.responseDocumentService = responseDocumentService;
        this.responseDetailService = responseDetailService;
    }

    @Override
    public void responseByCar(String userName, String password, String car) {
        /* try {
            Optional<DocumentTracking> opDocumentTracking = trackingService.findByCar(car);
            if (opDocumentTracking.isPresent()) {
                DocumentTracking documentTracking = opDocumentTracking.get();
                ResponseCeisa responseLogin = ceisaService.login(userName, password);
                if (responseLogin != null) {
                    ResponseCeisa response = ceisaService.responseByCar(car, responseLogin.getItem().getAccessToken());
                    if (response != null) {
                        response.getDataStatus().forEach(status -> {
                            try {
                                StatusDetail statusDetail = new StatusDetail();
                                Date statusDatetime = DateUtil.toDate(status.getWaktuStatus(), DateUtil.YYYY_MM_DD_HHMMSS);
                                Optional<StatusDetail> opStatusDetail = statusDetailService.findByCarAndStatusCodeAndStatusDatetime(car, status.getKodeProses(), statusDatetime);
                                if (opStatusDetail.isPresent()) {
                                    statusDetail = opStatusDetail.get();
                                }
                                statusDetail.setCar(car);
                                statusDetail.setCreatedBy("SYS");
                                statusDetail.setCreatedDate(new Date());
                                statusDetail.setDescription(status.getKeterangan().toUpperCase());
                                statusDetail.setDocumentCode(documentTracking.getDocumentTypeCode());
                                statusDetail.setRegisterDate(DateUtil.toDate(status.getTanggalDaftar(), DateUtil.DD_MM_YYYY));
                                statusDetail.setRegisterNumber(status.getNomorDaftar());
                                statusDetail.setStatusCode(status.getKodeProses());
                                statusDetail.setStatusDatetime(statusDatetime);
                                statusDetail.setTrackingId(documentTracking.getTrackingId());
                                statusDetailService.saveOrUpdate(statusDetail);

                                if (statusDetail.getStatusDatetime().after(documentTracking.getStatusDate())) {
                                    documentTracking.setStatusCode(statusDetail.getStatusCode());
                                    documentTracking.setStatusDate(statusDetail.getStatusDatetime());
                                    trackingService.saveOrUpdate(documentTracking);
                                }
                            } catch (Exception e) {
                                log.error(e.getLocalizedMessage(), e);
                            }
                        });

                        response.getDataRespon().forEach(respon -> {
                            try {
                                Optional<ResponseHeader> opResponseHeader = responseHeaderService.findByCarAndResponseCodeAndResponseDatetime(respon.getNomorAju(), respon.getKodeRespon(), respon.getWaktuRespon());
                                ResponseHeader responseHeader = new ResponseHeader();
                                if (opResponseHeader.isPresent()) {
                                    responseHeader = opResponseHeader.get();
                                }
                                responseHeader.setCar(respon.getNomorAju());
                                responseHeader.setDocumentCode(respon.getKodeDokumen());
                                responseHeader.setRegisterDate(respon.getTanggalDaftar());
                                responseHeader.setRegisterNumber(respon.getNomorDaftar());
                                responseHeader.setResponseCode(respon.getKodeRespon());
                                MstResponseDocument mstResponseDocument = responseDocumentService.getByCode(respon.getKodeRespon());
                                responseHeader.setDescription(mstResponseDocument == null ? null : mstResponseDocument.getResponseName());
                                responseHeader.setResponseDate(respon.getWaktuRespon());
                                responseHeader.setResponseDatetime(respon.getWaktuRespon());
                                responseHeader.setResponseNumber(respon.getNomorRespon());
                                responseHeader.setTrackingId(documentTracking.getTrackingId());
                                if (StringUtils.isNotBlank(respon.getPdf()) && StringUtils.isBlank(responseHeader.getFilepath())) {
                                    setFilepath(responseHeader, respon, responseLogin.getItem().getAccessToken());
                                }
                                responseHeaderService.saveOrUpdate(responseHeader);

                                int seri = 1;
                                for (String message : respon.getPesan()) {
                                    ResponseDetailPK responseDetailPK = new ResponseDetailPK();
                                    responseDetailPK.setResponseId(responseHeader.getResponseId());
                                    responseDetailPK.setSeri(seri++);
                                    com.rtm.pabean.model.tracking.ResponseDetail responseDetail = new com.rtm.pabean.model.tracking.ResponseDetail();
                                    responseDetail.setResponseDetailPK(responseDetailPK);
                                    responseDetail.setMessage(message);
                                    responseDetailService.saveOrUpdate(responseDetail);
                                }
                            } catch (Exception e) {
                                log.error(e.getLocalizedMessage(), e);
                            }
                        });
                    } else {
                        throw new Exception("GAGAL TARIK RESPONSE, KONEKSI TERPUTUS");
                    }
                } else {
                    throw new Exception("GAGAL TARIK RESPONSE, PASTIKAN USERNAME ATAU PASSWORD BENAR");
                }
            } else {
                throw new Exception(String.format("Document tracking not found with car [%s]", car));
            }
        } catch (Exception e) {
            // TODO: handle exception
        } */
    }

    private void setFilepath(ResponseHeader responseHeader, ResponseDetail respon, String token) {
        try {
            String filename = UUID.randomUUID().toString() + ".pdf";
            String base64 = ceisaService.downloadResponseS3(token, respon.getPdf());
            if (base64 == null) {
                Path path = ceisaService.downloadResponse(token, respon.getPdf());
                if (path != null) {
                    File file = new File(path.toString() + "/" + filename);

                    String targetPath = FileUtil.generateDirDate(responseFolder) + "/" + file.getName();
                    Files.move(Paths.get(file.getAbsolutePath()), Paths.get(targetPath), StandardCopyOption.REPLACE_EXISTING);
                    
                    responseHeader.setFilepath(targetPath);
                    FileUtil.deleteFile(path.toString());
                }
            } else {
                String path = FileUtil.base64ToFile(responseFolder, base64, filename);
                if (StringUtils.isNotBlank(path)) {
                    responseHeader.setFilepath(path.toString());
                    try {
                        FileUtil.deleteFile(path.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
    
}

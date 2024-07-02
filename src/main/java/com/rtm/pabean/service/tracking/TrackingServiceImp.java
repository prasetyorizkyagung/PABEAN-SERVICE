package com.rtm.pabean.service.tracking;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.tracking.DocumentTrackingDao;
import com.rtm.pabean.model.tracking.DocumentTracking;

@Service
public class TrackingServiceImp implements TrackingService {

    private DocumentTrackingDao documentTrackingDao;

    @Autowired
    public TrackingServiceImp(DocumentTrackingDao documentTrackingDao) {
        this.documentTrackingDao = documentTrackingDao;
    }
    
    @Override
    @Transactional
    public void saveOrUpdate(DocumentTracking documentTracking) throws Exception {
        if (documentTracking == null) {
            throw new Exception("Entity documentTracking kosong");
        }
        documentTrackingDao.save(documentTracking);
    }

    @Override
    public Optional<DocumentTracking> getByOrderId(String orderId) throws Exception {
        if (StringUtils.isBlank(orderId)) {
            throw new Exception("orderId kosong");
        }
        return documentTrackingDao.findByOrderId(orderId);
    }

    @Override
    public Optional<DocumentTracking> getByCar(String car) throws Exception {
        if (StringUtils.isBlank(car)) {
            throw new Exception("Car kosong");
        }
        return documentTrackingDao.findByCar(car);
    }

    /* @Override
    @Transactional(readOnly = true)
    public Optional<DocumentTracking> findDocByCar(String car, String docType) throws Exception {
        if (StringUtils.isBlank(car) && StringUtils.isBlank(docType)) {
            throw new Exception("Car atau DocType kosong");
        }
        return documentTrackingDao.findByCarAndDocumentTypeCode(car, docType);
    }

    @Override
    public Optional<DocumentTracking> findByCar(String car) throws Exception {
        if (StringUtils.isBlank(car)) {
            throw new Exception("Car kosong");
        }
        return documentTrackingDao.findByCar(car);
    }

    @Override
    public Optional<DocumentTracking> findCarByOrder(String orderId, String docType) throws Exception {
        if (StringUtils.isEmpty(orderId)) {
            throw new Exception("orderId kosong");
        }
        return documentTrackingDao.findByOrderIdAndDocumentTypeCode(orderId, docType);
    } */
    
}

package com.rtm.pabean.service.tracking;

import java.util.Optional;

import com.rtm.pabean.model.tracking.DocumentTracking;

public interface TrackingService {
    
    void saveOrUpdate(DocumentTracking documentTracking) throws Exception;

    Optional<DocumentTracking> getByOrderId(String orderId) throws Exception;

    Optional<DocumentTracking> getByCar(String car) throws Exception;

    // Optional<DocumentTracking> findDocByCar(String car, String docType) throws Exception;

    // Optional<DocumentTracking> findByCar(String car) throws Exception;

    // Optional<DocumentTracking> findCarByOrder(String orderId, String docType) throws Exception;
}

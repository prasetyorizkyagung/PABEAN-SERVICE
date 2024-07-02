package com.rtm.pabean.dao.tracking;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.tracking.DocumentTracking;

@Repository
public interface DocumentTrackingDao extends JpaRepository<DocumentTracking, String> {

        Optional<DocumentTracking> findByCarAndDocumentTypeCode(String car, String documentTypeCode);

        Optional<DocumentTracking> findByCar(String car);

        Optional<DocumentTracking> findByOrderId(String orderId);

        Optional<DocumentTracking> findByOrderIdAndDocumentTypeCode(String orderId, String documentTypeCode);
}

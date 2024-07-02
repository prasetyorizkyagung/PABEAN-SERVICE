package com.rtm.pabean.dao.tracking;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.tracking.StatusDetail;

@Repository
public interface StatusDetailDao extends JpaRepository<StatusDetail, String> {
    
    Optional<StatusDetail> findByCarAndStatusCodeAndStatusDatetime(String car, String statusCode, Date date);
}

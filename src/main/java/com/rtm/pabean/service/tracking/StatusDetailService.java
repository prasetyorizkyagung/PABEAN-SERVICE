package com.rtm.pabean.service.tracking;

import java.util.Date;
import java.util.Optional;

import com.rtm.pabean.model.tracking.StatusDetail;

public interface StatusDetailService {
    
    void saveOrUpdate(StatusDetail statusDetail) throws Exception;

    Optional<StatusDetail> findByCarAndStatusCodeAndStatusDatetime(String car, String statusCode, Date date) throws Exception;
}

package com.rtm.pabean.service.tracking;

import java.util.Date;
import java.util.Optional;

import com.rtm.pabean.model.tracking.ResponseHeader;

public interface ResponseHeaderService {

    void saveOrUpdate(ResponseHeader responseHeader) throws Exception;

    Optional<ResponseHeader> findByCarAndResponseCodeAndResponseDatetime(String car, String responseCode, Date responseDatetime);
}
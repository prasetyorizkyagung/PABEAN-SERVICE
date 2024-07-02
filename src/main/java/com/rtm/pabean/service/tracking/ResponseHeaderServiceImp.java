package com.rtm.pabean.service.tracking;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rtm.pabean.dao.tracking.ResponseHeaderDao;
import com.rtm.pabean.model.tracking.ResponseHeader;

@Service
public class ResponseHeaderServiceImp implements ResponseHeaderService {


    private ResponseHeaderDao responseHeaderDao;

    @Autowired
    public ResponseHeaderServiceImp(ResponseHeaderDao responseHeaderDao) {
        this.responseHeaderDao = responseHeaderDao;
    }

    @Override
    public void saveOrUpdate(ResponseHeader responseHeader) throws Exception {
        if (responseHeader == null) {
            throw new Exception("Entity responseHeader kosong");
        }
        responseHeaderDao.save(responseHeader);
    }

    @Override
    public Optional<ResponseHeader> findByCarAndResponseCodeAndResponseDatetime(String car, String responseCode, Date responseDatetime) {
        return responseHeaderDao.findByCarAndResponseCodeAndResponseDatetime(car, responseCode, responseDatetime);
    }
    
}

package com.rtm.pabean.service.tracking;

import org.springframework.stereotype.Service;

import com.rtm.pabean.dao.tracking.ResponseDetailDao;
import com.rtm.pabean.model.tracking.ResponseDetail;

@Service
public class ResponseDetailServiceImp implements ResponseDetailService {

    private ResponseDetailDao responseDetailDao;

    public ResponseDetailServiceImp(ResponseDetailDao responseDetailDao) {
        this.responseDetailDao = responseDetailDao;
    }

    @Override
    public void saveOrUpdate(ResponseDetail responseDetail) throws Exception {
        if (responseDetail == null) {
            throw new Exception("Entity responseDetail kosong");
        }
        responseDetailDao.save(responseDetail);
    }
    
}

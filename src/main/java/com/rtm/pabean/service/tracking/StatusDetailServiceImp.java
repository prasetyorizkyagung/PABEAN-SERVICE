package com.rtm.pabean.service.tracking;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.tracking.StatusDetailDao;
import com.rtm.pabean.model.tracking.StatusDetail;

@Service
public class StatusDetailServiceImp implements StatusDetailService {

    private StatusDetailDao statusDetailDao;

    @Autowired
    public StatusDetailServiceImp(StatusDetailDao statusDetailDao) {
        this.statusDetailDao = statusDetailDao;
    }

    @Override
    @Transactional
    public void saveOrUpdate(StatusDetail statusDetail) throws Exception {
        if (statusDetail == null) {
            throw new Exception("Entity statusDetail kosong");
        }
        statusDetailDao.save(statusDetail);
    }

    @Override
    public Optional<StatusDetail> findByCarAndStatusCodeAndStatusDatetime(String car, String statusCode, Date date) throws Exception {
        return statusDetailDao.findByCarAndStatusCodeAndStatusDatetime(car, statusCode, date);
    }
    
}

package com.rtm.pabean.service.order;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rtm.pabean.dao.order.JobOrderDao;
import com.rtm.pabean.model.order.JobOrder;

@Service
public class JobOrderServiceImp implements JobOrderService {

    private JobOrderDao jobOrderDao;

    @Autowired
    public JobOrderServiceImp(JobOrderDao jobOrderDao) {
        this.jobOrderDao = jobOrderDao;
    }

    @Override
    public Optional<JobOrder> findByOrderId(String orderId) {
        return jobOrderDao.findByOrderId(orderId);
    }

    @Override
    @Transactional
    public void updateStatus(String orderId, int status, String remark) throws Exception {
        if (orderId == null && status == 0) {
            throw new Exception("orderId and Status cannot be null");
        }
        jobOrderDao.updateStatus(status, remark, orderId);
    }
    
}

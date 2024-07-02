package com.rtm.pabean.service.order;

import java.util.Optional;

import com.rtm.pabean.model.order.JobOrder;

public interface JobOrderService {
    
    Optional<JobOrder> findByOrderId(String orderId);

    void updateStatus(String orderId, int status, String remark) throws Exception;
}

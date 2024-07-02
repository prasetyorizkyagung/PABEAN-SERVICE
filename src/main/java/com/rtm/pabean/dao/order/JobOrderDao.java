package com.rtm.pabean.dao.order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.order.JobOrder;

@Repository
public interface JobOrderDao extends JpaRepository<JobOrder, String> {
    
    Optional<JobOrder> findByOrderId(String orderId);

    @Modifying(clearAutomatically = true)
    @Query("update JobOrder i set i.orderStatus = :orderStatus, i.remark = :remark where i.orderId = :orderId")
    int updateStatus(int orderStatus, String remark, String orderId);
}

package com.rtm.pabean.dao.tracking;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.tracking.ResponseDetail;

@Repository
public interface ResponseDetailDao extends JpaRepository<ResponseDetail, String> {
    
}

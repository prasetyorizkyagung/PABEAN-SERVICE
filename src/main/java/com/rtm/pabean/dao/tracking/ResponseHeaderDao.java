package com.rtm.pabean.dao.tracking;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.tracking.ResponseHeader;

@Repository
public interface ResponseHeaderDao extends JpaRepository<ResponseHeader, String> {
    
    Optional<ResponseHeader> findByCarAndResponseCodeAndResponseDatetime(String car, String responseCode, Date responseDatetime);
}

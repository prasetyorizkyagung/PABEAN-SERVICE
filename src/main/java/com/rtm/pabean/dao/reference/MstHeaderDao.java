package com.rtm.pabean.dao.reference;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstHeader;

@Repository
public interface MstHeaderDao extends JpaRepository<MstHeader, String>{
    
}

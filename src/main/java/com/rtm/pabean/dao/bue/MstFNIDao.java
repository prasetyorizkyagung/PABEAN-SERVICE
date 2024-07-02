package com.rtm.pabean.dao.bue;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.MstFNI;

@Repository
public interface MstFNIDao extends JpaRepository<MstFNI, String>{
    
    Optional<MstFNI> findByCountryCodeAndTransportType(String countryCode, int transportType);
}

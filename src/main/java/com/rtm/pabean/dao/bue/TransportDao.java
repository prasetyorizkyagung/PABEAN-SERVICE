package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.Transport;
import com.rtm.pabean.model.bue.BueTransportPK;

@Repository("bueTransportDao")
public interface TransportDao extends JpaRepository<Transport, BueTransportPK> {

    List<Transport> findByTransportPKPebId(String pebId);

    long deleteByTransportPKPebId(String pebId);
    
    long countByTransportPKPebId(String pebId);
}

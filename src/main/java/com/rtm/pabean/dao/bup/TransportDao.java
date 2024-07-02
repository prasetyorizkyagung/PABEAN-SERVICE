package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.Transport;
import com.rtm.pabean.model.bup.BupTransportPK;

@Repository("bupTransportDao")
public interface TransportDao extends JpaRepository<Transport, BupTransportPK> {
    
    List<Transport> findByTransportPKPibId(String pibId);

    long deleteByTransportPKPibId(String pibId);
}

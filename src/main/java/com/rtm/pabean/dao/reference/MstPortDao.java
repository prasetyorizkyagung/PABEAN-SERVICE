package com.rtm.pabean.dao.reference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstPort;

@Repository
public interface MstPortDao extends JpaRepository<MstPort, String>{
    
    List<MstPort> findByCountryCode(String countryCode);

    List<MstPort> findByNameContainingIgnoreCase(String name);

    List<MstPort> findFirst10ByOrderByCode();

    @Query(value = "select * "
            + "from reference.mst_port "
            + "where upper(name) like %:parameter% "
            + "union "
            + "select * "
            + "from reference.mst_port "
            + "where upper(code) like %:parameter%", nativeQuery = true)
    List<MstPort> findByNameOrCode(String parameter);
}

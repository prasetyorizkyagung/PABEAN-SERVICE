package com.rtm.pabean.dao.reference;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstKpbc;

@Repository
public interface MstKpbcDao extends JpaRepository<MstKpbc, String>{
    
    Optional<MstKpbc> findByPortCode(String portCode);

    List<MstKpbc> findByNameContaining(String name);

    List<MstKpbc> findFirst10ByOrderByCode();

    @Query(value = "select * "
            + "from reference.mst_kpbc "
            + "where upper(name) like %:parameter% "
            + "union "
            + "select * "
            + "from reference.mst_kpbc "
            + "where upper(code) like %:parameter%", nativeQuery = true)
    List<MstKpbc> findByNameOrCode(String parameter);
}

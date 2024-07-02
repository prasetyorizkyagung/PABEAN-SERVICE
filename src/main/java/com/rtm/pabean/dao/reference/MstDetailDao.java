package com.rtm.pabean.dao.reference;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.reference.MstDetail;
import com.rtm.pabean.model.reference.MstDetailPK;

@Repository
public interface MstDetailDao extends JpaRepository<MstDetail, MstDetailPK> {

    List<MstDetail> findByMstDetailPKHeaderIdOrderBySeri(String headerId);

    List<MstDetail> findByMstDetailPKHeaderIdAndNameContaining(String headerId, String name);

    @Query(value = "select * "
            + "from reference.mst_detail "
            + "where header_id = :headerId and upper(name) like %:parameter% "
            + "union "
            + "select * "
            + "from reference.mst_detail "
            + "where header_id = :headerId and upper(code) like %:parameter%", nativeQuery = true)
    List<MstDetail> findByNameOrCode(String headerId, String parameter);
}

package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.BupSpecialSpecificationPK;
import com.rtm.pabean.model.bup.SpecialSpecifications;

@Repository("bupSpecialSpecificationsDao")
public interface SpecialSpecificationsDao extends JpaRepository<SpecialSpecifications, BupSpecialSpecificationPK> {

    List<SpecialSpecifications> findBySpecialSpecificationPKItemId(String itemId);

    long deleteBySpecialSpecificationPKItemId(String itemId);

    @Query(value = "select b.item_id, b.specification_code, b.description "
            + "from import.item_detail a "
            + "left join import.special_specifications b on a.item_id = b.item_id "
            + "where a.pib_id = :pibId "
            + "order by a.item_id asc ", nativeQuery = true)
    List<Object[]> findBySpecialSpecificationPibId(String pibId);

}

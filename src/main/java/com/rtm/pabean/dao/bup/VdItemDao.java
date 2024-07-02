package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.VdItem;
import com.rtm.pabean.model.bup.BupVdItemPK;

@Repository("bupVdItemDao")
public interface VdItemDao extends JpaRepository<VdItem, BupVdItemPK> {

    List<VdItem> findByVdItemPKItemId(String itemId);

    long deleteByVdItemPKItemId(String itemId);

    @Query(value = "select b.item_id, b.vd_type, b.vd_value, b.due_date "
            + "from import.item_detail a "
            + "left join import.vd_item b on a.item_id = b.item_id "
            + "where a.pib_id = :pibId order by a.item_id asc", nativeQuery = true)
    List<Object[]> findByPibDetail(String pibId);
}

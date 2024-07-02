package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.ItemEntity;
import com.rtm.pabean.model.bue.BueItemEntityPK;


@Repository("bueItemEntityDao")
public interface ItemEntityDao extends JpaRepository<ItemEntity, BueItemEntityPK> {

        List<ItemEntity> findByItemEntityPKItemId(String itemId);

        long deleteByItemEntityPKItemId(String itemId);

        long deleteByItemEntityPKEntityIdAndItemEntityPKItemId(String entityId, String itemId);

        long deleteByItemEntityPKEntityId(String entityId);
}

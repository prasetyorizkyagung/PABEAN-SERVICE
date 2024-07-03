package com.rtm.pabean.dao.bue;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.ItemDetail;

@Repository("bueItemDetailDao")
public interface ItemDetailDao extends JpaRepository<ItemDetail, String> {

        List<ItemDetail> findByPebId(String pebId);

        List<ItemDetail> findByPebIdOrderBySeri(String pebId);

        long deleteByPebId(String pebId);

        long countByPebId(String pebId);

        @Query(value = "select coalesce(sum(id.netto),0) "
                        + "from export.item_detail id "
                        + "where peb_id = :pebId", nativeQuery = true)
        BigDecimal sumNetto(String pebId);

}

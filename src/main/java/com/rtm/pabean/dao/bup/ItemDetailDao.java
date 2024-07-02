package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.ItemDetail;

@Repository("bupItemDetailDao")
public interface ItemDetailDao extends JpaRepository<ItemDetail, String> {

        long countByPibId(String pibId);

        List<ItemDetail> findByPibIdOrderByItemId(String pibId);

        List<ItemDetail> findByPibId(String pibId);

        long deleteByPibId(String pibId);

        @Query(value = "select count(item_id) "
                        + "from import.item_detail id "
                        + "where pib_id = :pibId and coalesce(hs_code,'') like %:hsCode% and coalesce(description,'') like %:description% and coalesce(cast(unit_price as varchar),'') like %:unitPrice% "
                        + "and coalesce(cast(number_unit as varchar),'') like %:numberUnit% and coalesce(item_unit,'') like %:itemUnit%", nativeQuery = true)
        long countByField(String pibId, String hsCode, String description, String unitPrice,
                        String numberUnit, String itemUnit);

        @Query(value = "select item_id, hs_code "
                        + "from import.item_detail "
                        + "where pib_id = :pibId", nativeQuery = true)
        List<Object[]> findTarifByPibId(String pibId);

        @Query(value = "select distinct id.item_id "
                        + "from import.item_detail id "
                        + "inner join import.tariff_item ti on id.item_id = ti.item_id "
                        + "where id.pib_id = :pibId and id.hs_code = :hsCode "
                        + "and ti.tax_type = :taxType and ti.tariff_value = :tariffValue", nativeQuery = true)
        List<Object[]> findItemIdByTarif(String pibId, String hsCode, String taxType, double tariffValue);

        @Query(value = "select i.* "
                        + "from import.item_detail i "
                        + "where i.pib_id = :pibId "
                        + "order by item_id offset :offset limit :limit", nativeQuery = true)
        List<ItemDetail> findByPibIdPaging2(String pibId, int offset, int limit);
}

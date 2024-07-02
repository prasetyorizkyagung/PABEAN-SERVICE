package com.rtm.pabean.dao.bup;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bup.TariffItem;
import com.rtm.pabean.model.bup.BupTariffItemPK;

@Repository("bupTariffItemDao")
public interface TariffItemDao extends JpaRepository<TariffItem, BupTariffItemPK> {

    List<TariffItem> findByTariffItemPKItemId(String itemId);

    List<TariffItem> findByTariffItemPKItemIdOrderByTariffItemPKTaxType(String itemId);

    long deleteByTariffItemPKItemId(String itemId);

    @Query(value = "select ph.car, ti.tax_type, ti.tariff_facility_code, cast(sum(ti.payment_value) as int8) paid, cast(sum(ti.facility_value) as int8) facility "
            + "from import.tariff_item ti "
            + "inner join import.item_detail id on id.item_id = ti.item_id "
            + "inner join import.pib_header ph on ph.pib_id = id.pib_id "
            + "where ph.pib_id = :pibId "
            + "group by ph.car, ti.tax_type, ti.tariff_facility_code", nativeQuery = true)
    List<Object[]> findTaxByPibId(String pibId);

    @Query(value = "select b.tax_type, b.tariff_facility_code, sum(b.payment_value) as value "
            + "from import.item_detail a "
            + "inner join import.tariff_item b on a.item_id = b.item_id "
            + "where a.pib_id = :pibId "
            + "group by b.tax_type, b.tariff_facility_code", nativeQuery = true)
    List<Object[]> findPgtByPibId(String pibId);

    @Query(value = "select ti.number_unit "
            + "from import.tariff_item ti "
            + "where ti.item_id = :itemId "
            + "and ti.tax_type = 'BM'", nativeQuery = true)
    List<Object[]> findSatByItemId(String itemId);

    @Query(value = "select b.item_id, b.tariff_type, b.number_unit, b.tariff_facility_code, b.tax_type, b.tariff_value, b.facility_tariff, "
            + "b.payment_value, b.unit_code, b.is_temporary, b.facility_value, a.hs_code "
            + "from import.item_detail a "
            + "inner join import.tariff_item  b on a.item_id = b.item_id "
            + "where a.pib_id = :pibId "
            + "order by a.item_id asc", nativeQuery = true)
    List<Object[]> findTarifByPibId(String pibId);

    @Query(value = "select distinct hs_code "
            + "from import.item_detail "
            + "where pib_id = :pibId ", nativeQuery = true)
    List<Object[]> findHsByPibId(String pibId);

    @Query(value = "select item_id "
            + "from import.item_detail "
            + "where pib_id = :pibId and hs_code = :hsCode", nativeQuery = true)
    List<Object[]> findItemIdByPibId(String pibId, String hsCode);

    @Query(value = "select b.item_id, b.tariff_type, b.number_unit, b.tariff_facility_code, b.tax_type, b.tariff_value, b.facility_tariff, "
            + "b.payment_value, b.unit_code, b.is_temporary, b.facility_value "
            + "from import.tariff_item b "
            + "where b.item_id = :itemId", nativeQuery = true)
    List<Object[]> findTarifByItemId(String itemId);
}

package com.rtm.pabean.dao.bue;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.rtm.pabean.model.bue.TariffItem;
import com.rtm.pabean.model.bue.BueTariffItemPK;

@Repository("bueTariffItemDao")
public interface TariffItemDao extends JpaRepository<TariffItem, BueTariffItemPK> {

    List<TariffItem> findByTariffItemPKItemId(String itemId);

    long deleteByTariffItemPKItemId(String itemId);

    @Query(value = "select ph.car, ti.tax_type, ti.tariff_facility_code, sum(ti.payment_value) paid, sum(ti.facility_value) facility "
            + "from export.tariff_item ti "
            + "inner join export.item_detail id on id.item_id = ti.item_id "
            + "inner join export.peb_header ph on ph.peb_id = id.peb_id "
            + "where ph.peb_id = :pebId "
            + "group by ph.car, ti.tax_type, ti.tariff_facility_code", nativeQuery = true)
    List<Object[]> findTaxByPebId(String pebId);
}

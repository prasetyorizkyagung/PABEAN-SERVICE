package com.rtm.pabean.model.bue;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueTariffItem")
@Data
@Table(schema = "export", name = "tariff_item")
public class TariffItem {

    @EmbeddedId
    private BueTariffItemPK tariffItemPK;
    private String tariffType, tariffFacilityCode, unitCode;
    private BigDecimal numberUnit, facilityTariff, paymentValue, facilityValue, tariffValue;
}

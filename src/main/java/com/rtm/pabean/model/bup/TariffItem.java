package com.rtm.pabean.model.bup;

import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupTariffItem")
@Data
@Table(schema = "import", name = "tariff_item")
public class TariffItem {

    @EmbeddedId
    private BupTariffItemPK tariffItemPK;
    private BigDecimal tariffValue, facilityTariff, paymentValue, numberUnit, facilityValue;
    private String tariffType, tariffFacilityCode, unitCode;
    private boolean isTemporary;
}

package com.rtm.pabean.model.bup;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupItemDetail")
@Data
@Table(schema = "import", name = "item_detail")
public class ItemDetail {

    @Id
    private String itemId;
    private String pibId, itemCondition, brand, packageType, originCountry, itemUnit, description, type, hsCode, size,
            otherSpecification, itemCode;
    private BigDecimal insuranceValue, cif, fob, freight, unitPrice, numberUnit, initialBalance, latestBalance, netto;
    private int numberPackage, seri;
    private boolean isLartas;

    public BigDecimal getInsuranceValue() {
        if (this.insuranceValue == null) {
            this.insuranceValue = BigDecimal.ZERO;
        }
        return this.insuranceValue.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCif() {
        if (this.cif == null) {
            this.cif = BigDecimal.ZERO;
        }
        return this.cif.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFob() {
        if (this.fob == null) {
            this.fob = BigDecimal.ZERO;
        }
        return this.fob.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getFreight() {
        if (this.freight == null) {
            this.freight = BigDecimal.ZERO;
        }
        return this.freight.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getUnitPrice() {
        if (this.unitPrice == null) {
            this.unitPrice = BigDecimal.ZERO;
        }
        return this.unitPrice.setScale(2, RoundingMode.HALF_UP);
    }

    public ItemDetail() {
        this.itemId = UUID.randomUUID().toString();
    }

}

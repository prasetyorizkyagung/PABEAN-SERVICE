package com.rtm.pabean.model.bue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity(name = "BueItemDetail")
@Data
@Table(schema = "export", name = "item_detail")
@DynamicUpdate
public class ItemDetail {

    @Id
    private String itemId;
    private BigInteger numberPackage;
    private String hsCode, itemCode, description, brand, type, size, originCountry, originPlace, unitCode, packageType,
            pebId;
    private BigDecimal unitPrice, numberUnit, fob, netto, volume, palmValue, standardPrice, ndpbm;
    @Column(name = "is_lartas")
    private boolean lartas;

    public ItemDetail() {
        this.itemId = UUID.randomUUID().toString();
    }
}

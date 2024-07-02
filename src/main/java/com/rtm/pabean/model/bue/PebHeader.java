package com.rtm.pabean.model.bue;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity(name = "BuePebHeader")
@DynamicInsert
@DynamicUpdate
@Table(schema = "export", name = "peb_header")
public class PebHeader {

    @Id
    private String pebId;
    private String orderId, idHeader, car, insuranceCode, paymentMethod, paymentDescription, exportType, customsOffice, exportCategory,
            unloadingPort, loadingPort, exportLoadingPort, destinationPort, currency, signPosition, signName, signCity,
            incotermCode, tradeType, bulkGoods, commodity, tpsCode, countryDestination, locationCheckCode, officeCheck,
            loadingOffice, exportOffice;
    private BigDecimal insuranceValue, brutto, fob, freight, ndpbm, netto, maklonValue, palmValue;
    private Date exportDate, dateCheck, signDate;
    private Date createdDate;
    private int status;

    public PebHeader() {
        this.pebId = UUID.randomUUID().toString();
    }
}

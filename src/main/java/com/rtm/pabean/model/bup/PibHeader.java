package com.rtm.pabean.model.bup;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity(name = "BupPibHeader")
@Data
@Table(schema = "import", name = "pib_header")
@DynamicInsert
@DynamicUpdate
public class PibHeader {

    @Id
    private String pibId;
    private String car, procedureType, importType, paymentMethod, incotermCode, transactionType, customsOffice,
            loadingPort, destinationPort, transitPort, tpsCode, currency, signPosition, signName, signCity,
            insuranceCode;
    private String idHeader;
    private int status;
    @Column(name = "bc11_number")
    private String bc11Number;
    @Column(name = "bc11_pos")
    private String bc11Pos;
    @Column(name = "bc11_subpos")
    private String bc11Subpos;
    private BigDecimal brutto, netto, cif, vdValue, freight, insuranceValue, additionalCost, reductionCost, ndpbm;
    private boolean isVd;
    private Date arrivalDate, signDate;
    @Column(name = "bc11_date")
    private Date bc11Date;
    private Date createdDate;
    private String orderId;

    public PibHeader() {
        this.pibId = UUID.randomUUID().toString();
    }
}

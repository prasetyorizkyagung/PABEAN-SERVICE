package com.rtm.pabean.model.order;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "order_")
public class JobOrder {
    
    @Id
    private String orderId;
    private String jobNumber, customer, customerId, company, referenceNumber, remark, createdBy, updatedBy,
            customsOffice, userId, companyTaxNumber;
    @Column(name = "bc10_number")
    private String bc10Number;
    private int transactionType, orderStatus, outputType, orderType, documentType;
    private Date eta, etd, createdDate, updatedDate;
    @Column(name = "bc10_date")
    private Date bc10Date;
    private boolean isSentBc, isApprove;
    private String parentOrderId;
}

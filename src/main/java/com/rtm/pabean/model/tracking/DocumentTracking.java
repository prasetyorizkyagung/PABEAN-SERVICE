package com.rtm.pabean.model.tracking;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity
@Data
@DynamicInsert
@DynamicUpdate
@Table(schema = "tracking")
public class DocumentTracking {

    @Id
    private String trackingId;
    private String orderId, car, documentTypeCode, statusCode, companyId, customsOfficeCode, registerNumber;
    private Date statusDate, registerDate;
    private Date createdDate;

    public DocumentTracking(){
        this.trackingId = UUID.randomUUID().toString();
    }
}

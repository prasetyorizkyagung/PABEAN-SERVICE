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
public class StatusDetail {

    @Id
    private String statusId;
    private String trackingId, car, registerNumber, statusCode, description, documentCode, createdBy;
    private Date registerDate, statusDatetime, createdDate;

    public StatusDetail() {
        this.statusId = UUID.randomUUID().toString();
    }
}

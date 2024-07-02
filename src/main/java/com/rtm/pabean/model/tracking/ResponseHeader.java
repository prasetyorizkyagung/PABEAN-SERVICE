package com.rtm.pabean.model.tracking;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Data
@Entity
@DynamicInsert
@DynamicUpdate
@Table(schema = "tracking")
public class ResponseHeader {

    @Id
    private String responseId;
    private String car, responseCode, responseNumber, registerNumber, description, filepath, documentCode, trackingId;
    private Date responseDate, responseDatetime, registerDate, createdDate;

    public ResponseHeader() {
        this.responseId = UUID.randomUUID().toString();
    }
}

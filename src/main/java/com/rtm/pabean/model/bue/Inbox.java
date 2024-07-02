package com.rtm.pabean.model.bue;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity(name = "BueInbox")
@Data
@DynamicInsert
@DynamicUpdate
@Table(schema = "export", name = "inbox")
public class Inbox {

    @Id
    private String inboxId;
    private String orderId, companyId, filePath, status, message;
    private Date createdDate;

    public Inbox() {
        this.inboxId = UUID.randomUUID().toString();
    }
}

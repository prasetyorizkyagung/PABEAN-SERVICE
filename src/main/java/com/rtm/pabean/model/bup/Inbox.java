package com.rtm.pabean.model.bup;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity(name = "BupInbox")
@Data
@DynamicInsert
@DynamicUpdate
@Table(schema = "import", name = "inbox")
public class Inbox {

    @Id
    private String inboxId;
    private String orderId, companyId, filePath, status, message;
    private Date createdDate;

    public Inbox() {
        this.inboxId = UUID.randomUUID().toString();
    }
}

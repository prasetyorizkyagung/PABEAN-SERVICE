package com.rtm.pabean.model.bup;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;

@Entity(name = "BupOutbox")
@Data
@Table(schema = "import", name = "outbox")
@DynamicInsert
@DynamicUpdate
public class Outbox {

    @Id
    private String outboxId;
    private String orderId, status, message, userName, password, car;
    @Column(name = "is_final")
    private boolean final_;
    private Date createdDate;

    public Outbox() {
        this.outboxId = UUID.randomUUID().toString();
    }
}

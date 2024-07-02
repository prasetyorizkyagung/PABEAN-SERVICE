package com.rtm.pabean.model.reference;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "reference")
public class MstTps {

    @Id
    private String id;
    private String code, name, customsOffice;
    private boolean isActive;

    public MstTps() {
        this.id = UUID.randomUUID().toString();
    }
}

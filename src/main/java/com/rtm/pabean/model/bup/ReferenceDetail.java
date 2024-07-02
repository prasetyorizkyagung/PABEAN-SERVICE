package com.rtm.pabean.model.bup;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupReferenceDetail")
@Data
@Table(schema = "import", name = "reference_detail")
public class ReferenceDetail {

    @Id
    private String id;
    private String refCode;
    private String code;
    private String codeDescription;

    public ReferenceDetail() {
        this.id = UUID.randomUUID().toString();
    }
}

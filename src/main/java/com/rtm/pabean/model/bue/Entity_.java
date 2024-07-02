package com.rtm.pabean.model.bue;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueEntity")
@Data
@Table(schema = "export", name = "entity")
public class Entity_ {

    @Id
    private String entityId;
    private String pebId, identityType, nib, entityCode, countryCode, apiType, entityAddress, entityName, status,
            identityNumber;

    public Entity_() {
        this.entityId = UUID.randomUUID().toString();
    }

    public Entity_(String entityId) {
        this.entityId = entityId;
    }
}

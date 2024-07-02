package com.rtm.pabean.model.bup;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupEntity")
@Data
@Table(schema = "import", name = "entity")
public class Entity_ {

    @EmbeddedId
    private BupEntityPK entityPK;
    private String identityType, nib, countryCode, apiType, entityAddress, entityName, status,
            identityNumber;
}

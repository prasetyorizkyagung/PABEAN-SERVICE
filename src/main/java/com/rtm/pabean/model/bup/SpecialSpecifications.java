package com.rtm.pabean.model.bup;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupSpecialSpecifications")
@Data
@Table(schema = "import", name = "special_specifications")
public class SpecialSpecifications {

    @EmbeddedId
    private BupSpecialSpecificationPK specialSpecificationPK;
    private String description;

}

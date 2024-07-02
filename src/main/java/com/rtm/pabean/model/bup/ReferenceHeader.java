package com.rtm.pabean.model.bup;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupReferenceHeader")
@Data
@Table(schema = "import", name = "reference_header")
public class ReferenceHeader {
    
    @Id
    private String refCode;
    private String refDescription;
}

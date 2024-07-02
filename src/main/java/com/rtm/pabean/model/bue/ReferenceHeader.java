package com.rtm.pabean.model.bue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueReferenceHeader")
@Data
@Table(schema = "import", name = "reference_header")
public class ReferenceHeader {
    
    @Id
    private String refCode;
    private String refDescription;
}

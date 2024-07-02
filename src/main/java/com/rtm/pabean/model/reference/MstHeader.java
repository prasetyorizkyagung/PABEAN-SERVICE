package com.rtm.pabean.model.reference;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "reference")
public class MstHeader {
    
    @Id
    private String headerId;
    private String name;
}

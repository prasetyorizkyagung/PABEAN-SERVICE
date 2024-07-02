package com.rtm.pabean.model.reference;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(schema = "reference")
public class MstDetail {
    
    @EmbeddedId
    private MstDetailPK mstDetailPK;
    private String name;
    private Integer seri;
}

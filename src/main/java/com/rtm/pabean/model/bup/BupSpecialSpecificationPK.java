package com.rtm.pabean.model.bup;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BupSpecialSpecificationPK implements Serializable{
    
    private String itemId, specificationCode;
}

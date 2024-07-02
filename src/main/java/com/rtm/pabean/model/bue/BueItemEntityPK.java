package com.rtm.pabean.model.bue;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BueItemEntityPK implements Serializable{
    
    private String itemId, entityId;
}

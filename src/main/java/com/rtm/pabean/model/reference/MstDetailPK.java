package com.rtm.pabean.model.reference;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class MstDetailPK implements Serializable{
    
    private String headerId, code;
}

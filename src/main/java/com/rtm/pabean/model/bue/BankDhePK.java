package com.rtm.pabean.model.bue;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BankDhePK implements Serializable {
    
    private String pebId, bankCode;
}

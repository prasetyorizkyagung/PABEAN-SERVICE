package com.rtm.pabean.model.bue;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueBankDhe")
@Data
@Table(schema = "export", name = "bank_dhe")
public class BankDhe {
    
    @EmbeddedId
    private BankDhePK bankDhePK;
    private String bankDescription;
}

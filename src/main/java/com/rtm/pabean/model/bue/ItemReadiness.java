package com.rtm.pabean.model.bue;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueItemReadiness")
@Data
@Table(schema = "export", name = "item_readiness")
public class ItemReadiness {

    @Id
    private String pkbId;
    private String pebId, itemTypeCode, tpsTypeCode, picName, address, phoneNumber, locationCheck, stuffingCode,
            codeTypePart;
    private int numberContainer20, numberContainer40;
    private Date pkbDate, dateCheck;

    public ItemReadiness() {
        this.pkbId = UUID.randomUUID().toString();
    }
}

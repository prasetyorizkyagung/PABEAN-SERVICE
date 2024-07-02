package com.rtm.pabean.model.bue;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class BueTariffItemPK implements Serializable {

    private String itemId;
    private String taxType;
}

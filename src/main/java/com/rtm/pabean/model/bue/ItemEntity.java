package com.rtm.pabean.model.bue;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueItemEntity")
@Data
@Table(schema = "export", name = "item_entity")
public class ItemEntity {
    
    @EmbeddedId
    private BueItemEntityPK itemEntityPK;
}

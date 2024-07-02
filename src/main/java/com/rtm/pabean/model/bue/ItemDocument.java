package com.rtm.pabean.model.bue;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueItemDocument")
@Data
@Table(schema = "export", name = "item_document")
public class ItemDocument {

    @EmbeddedId
    private BueItemDocumentPK itemDocumentPK;

}

package com.rtm.pabean.model.bup;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupItemDocument")
@Data
@Table(schema = "import", name = "item_document")
public class ItemDocument {

    @EmbeddedId
    private BupItemDocumentPK itemDocumentPK;
    private Integer itemSkepSeri;
}

package com.rtm.pabean.model.bup;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupDocument")
@Data
@Table(schema = "import", name = "document")
public class Document {

    @Id
    private String documentId;
    private String pibId, documentCode, documentNumber, facilityCode, permitCode;
    private Date documentDate;
    private int seri;

    public Document() {
        this.documentId = UUID.randomUUID().toString();
    }
}

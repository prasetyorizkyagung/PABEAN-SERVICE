package com.rtm.pabean.model.bue;

import java.util.Date;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueDocument")
@Data
@Table(schema = "export", name = "document")
public class Document {

    @Id
    private String documentId;
    private String pebId, documentCode, documentNumber, facilityCode;
    private Date documentDate;
    private int seri;

    public Document() {
        this.documentId = UUID.randomUUID().toString();
    }
}

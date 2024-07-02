package com.rtm.pabean.model.bue;

import java.io.Serializable;

import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class BueItemDocumentPK implements Serializable {

    private String itemId, documentId;
}

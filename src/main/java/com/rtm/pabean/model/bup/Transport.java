package com.rtm.pabean.model.bup;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupTransport")
@Data
@Table(schema = "import", name = "transport")
public class Transport {
    
    @EmbeddedId
    private BupTransportPK transportPK;
    private String transportType, flagCode, transportName;
}

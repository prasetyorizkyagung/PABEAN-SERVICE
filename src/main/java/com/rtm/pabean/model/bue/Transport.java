package com.rtm.pabean.model.bue;

import java.util.Optional;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueTransport")
@Data
@Table(schema = "export", name = "transport")
public class Transport {

    @EmbeddedId
    private BueTransportPK transportPK;
    private String flagCode, transportName, transportType;

    public boolean isNull() {
        return Optional.ofNullable(this.transportName).orElse("").isEmpty()
                && Optional.ofNullable(this.flagCode).orElse("").isEmpty() &&
                Optional.ofNullable(this.transportPK.getTransportNumber()).orElse("").isEmpty()
                && Optional.ofNullable(this.transportType).orElse("").isEmpty();
    }
}

package com.rtm.pabean.model.bup;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupContainer")
@Data
@Table(schema = "import", name = "container")
public class Container {

    @Id
    private String containerId;
    private String pibId, containerNumber, containerType, containerSize, containerCodeType;

    public Container() {
        this.containerId = UUID.randomUUID().toString();
    }
}

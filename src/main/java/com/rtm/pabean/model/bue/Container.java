package com.rtm.pabean.model.bue;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueContainer")
@Data
@Table(schema = "export", name = "container")
public class Container {

    @Id
    private String containerId;
    private String pebId, containerNumber, containerSize, containerType, containerCodeType;

    public Container() {
        this.containerId = UUID.randomUUID().toString();
    }
}

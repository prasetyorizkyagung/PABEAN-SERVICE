package com.rtm.pabean.model.bue;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BueMstFNI")
@Data
@Table(schema = "export", name = "mst_f_n_i")
public class MstFNI {

    @Id
    private String id;
    private int transportType;
    private String countryCode;
    private double freight, insurance;

    public MstFNI() {
        this.id = UUID.randomUUID().toString();
    }
}

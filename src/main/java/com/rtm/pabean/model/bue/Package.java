package com.rtm.pabean.model.bue;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BuePackage")
@Data
@Table(schema = "export", name = "package")
public class Package {

    @Id
    private String packageId;
    private int numberPack;
    private String pebId, packType, packBrand;

    public Package() {
        this.packageId = UUID.randomUUID().toString();
    }
}

package com.rtm.pabean.model.bup;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity(name = "BupPackage")
@Data
@Table(schema = "import", name = "package")
public class Package {

    @Id
    private String packageId;
    private int numberPack;
    private String pibId, packType, packBrand;

    public Package() {
        this.packageId = UUID.randomUUID().toString();
    }
}

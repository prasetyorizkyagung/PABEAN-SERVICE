package com.rtm.pabean.model.bue.module;

import java.math.BigInteger;

public class TblPebDhe {

    private String car, kdBank, urBank;
    private BigInteger seriDhe;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getKdBank() {
        return kdBank;
    }

    public void setKdBank(String kdBank) {
        this.kdBank = kdBank;
    }

    public String getUrBank() {
        return urBank;
    }

    public void setUrBank(String urBank) {
        this.urBank = urBank;
    }

    public BigInteger getSeriDhe() {
        return seriDhe;
    }

    public void setSeriDhe(BigInteger seriDhe) {
        this.seriDhe = seriDhe;
    }
}

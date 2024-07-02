package com.rtm.pabean.model.bue.module;

import java.util.Date;

public class TblPebDok {

    private String car, kdDok, noDok, kdKtrDok;

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getKdDok() {
        return kdDok;
    }

    public void setKdDok(String kdDok) {
        this.kdDok = kdDok;
    }

    public String getNoDok() {
        return noDok;
    }

    public void setNoDok(String noDok) {
        this.noDok = noDok;
    }

    public String getKdKtrDok() {
        return kdKtrDok;
    }

    public void setKdKtrDok(String kdKtrDok) {
        this.kdKtrDok = kdKtrDok;
    }

    public Date getTgDok() {
        return tgDok;
    }

    public void setTgDok(Date tgDok) {
        this.tgDok = tgDok;
    }
    private Date tgDok;
}

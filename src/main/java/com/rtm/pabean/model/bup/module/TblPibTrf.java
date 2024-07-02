package com.rtm.pabean.model.bup.module;

import java.util.Objects;

import lombok.Data;

@Data
public class TblPibTrf {

    private String car, noHs, kdTrpBM, kdSatBM, kdCuk, kdTrpCuk, kdSatCuk, kdTrpBMAD, kdTrpBMTP, kdTrpBMIM, kdTrpBMPB, kdCukSub, kdKmsCuk, flagTis, flagLekat;
    private int seriTrp;
    private double trpBM, trpCuk, trpPPN, trpPBM, trpPPH, trpBMAD, trpBMTP, trpBMIM, trpBMPB, hjeCuk, isiPerKmsCuk;

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TblPibTrf other = (TblPibTrf) obj;
        if (this.seriTrp != other.seriTrp) {
            return false;
        }
        if (!Objects.equals(this.car, other.car)) {
            return false;
        }
        if (!Objects.equals(this.noHs, other.noHs)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpBM, other.kdTrpBM)) {
            return false;
        }
        if (!Objects.equals(this.kdSatBM, other.kdSatBM)) {
            return false;
        }
        if (!Objects.equals(this.kdCuk, other.kdCuk)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpCuk, other.kdTrpCuk)) {
            return false;
        }
        if (!Objects.equals(this.kdSatCuk, other.kdSatCuk)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpBMAD, other.kdTrpBMAD)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpBMTP, other.kdTrpBMTP)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpBMIM, other.kdTrpBMIM)) {
            return false;
        }
        if (!Objects.equals(this.kdTrpBMPB, other.kdTrpBMPB)) {
            return false;
        }
        if (!Objects.equals(this.kdCukSub, other.kdCukSub)) {
            return false;
        }
        if (!Objects.equals(this.kdKmsCuk, other.kdKmsCuk)) {
            return false;
        }
        if (!Objects.equals(this.flagTis, other.flagTis)) {
            return false;
        }
        if (!Objects.equals(this.flagLekat, other.flagLekat)) {
            return false;
        }
        if (!Objects.equals(this.trpBM, other.trpBM)) {
            return false;
        }
        if (!Objects.equals(this.trpCuk, other.trpCuk)) {
            return false;
        }
        if (!Objects.equals(this.trpPPN, other.trpPPN)) {
            return false;
        }
        if (!Objects.equals(this.trpPBM, other.trpPBM)) {
            return false;
        }
        if (!Objects.equals(this.trpPPH, other.trpPPH)) {
            return false;
        }
        if (!Objects.equals(this.trpBMAD, other.trpBMAD)) {
            return false;
        }
        if (!Objects.equals(this.trpBMTP, other.trpBMTP)) {
            return false;
        }
        if (!Objects.equals(this.trpBMIM, other.trpBMIM)) {
            return false;
        }
        if (!Objects.equals(this.trpBMPB, other.trpBMPB)) {
            return false;
        }
        if (!Objects.equals(this.hjeCuk, other.hjeCuk)) {
            return false;
        }
        return Objects.equals(this.isiPerKmsCuk, other.isiPerKmsCuk);
    }

    @Override
    public String toString() {
        return "TblPibTrf{" + "car=" + car + ", noHs=" + noHs + ", kdTrpBM=" + kdTrpBM + ", kdSatBM=" + kdSatBM + ", kdCuk=" + kdCuk + ", kdTrpCuk=" + kdTrpCuk + ", kdSatCuk=" + kdSatCuk + ", kdTrpBMAD=" + kdTrpBMAD + ", kdTrpBMTP=" + kdTrpBMTP + ", kdTrpBMIM=" + kdTrpBMIM + ", kdTrpBMPB=" + kdTrpBMPB + ", kdCukSub=" + kdCukSub + ", kdKmsCuk=" + kdKmsCuk + ", flagTis=" + flagTis + ", flagLekat=" + flagLekat + ", seriTrp=" + seriTrp + ", trpBM=" + trpBM + ", trpCuk=" + trpCuk + ", trpPPN=" + trpPPN + ", trpPBM=" + trpPBM + ", trpPPH=" + trpPPH + ", trpBMAD=" + trpBMAD + ", trpBMTP=" + trpBMTP + ", trpBMIM=" + trpBMIM + ", trpBMPB=" + trpBMPB + ", hjeCuk=" + hjeCuk + ", isiPerKmsCuk=" + isiPerKmsCuk + '}';
    }

}

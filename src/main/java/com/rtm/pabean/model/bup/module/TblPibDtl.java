package com.rtm.pabean.model.bup.module;

import java.math.BigDecimal;
import java.math.BigInteger;

import lombok.Data;

@Data
public class TblPibDtl {

    private String car, noHs, brgUrai, merk, tipe, spfLain, brgAsal, kdSat, kemasJn, kdFasDtl, flBarangBaru, flLartas,
            katLartas, spekTarif;
    private BigInteger itemId, serial, seriTrp, kemasJm;
    private BigDecimal dNilInv, dCif, jmlSat, satBmJm, satCukJm, nettoDtl, dNilCuk, jmPC, saldoAwalPC, saldoAkhirPC;
    private boolean dtlOk;
}

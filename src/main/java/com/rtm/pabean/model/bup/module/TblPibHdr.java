package com.rtm.pabean.model.bup.module;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TblPibHdr {

    private String car, kdKpbc, pibNo, jnPib, jnImp, crByr, dokTupKd, dokTupNo, posNo, posSub, posSubSub, impId, impNpwp, impNama, impAlmt, impStatus, apiKd, apiNo, ppjkId, ppjkNpwp, ppjkNama, ppjkAlmt, ppjkNo,
            indId, indNpwp, indNama, indAlmt, pasokNama, pasokAlmt, pasokNeg, pelBkr, pelMuat, pelTransit, tmpTbn, moda, angkutNama, angkutNo, angkutFl, kdVal, kdAss, kdHrg, status, snrf, kdFas, billNpwp, billNama,
            billAlmt, penjualNama, penjualAlmt, penjualNeg, pernyataan, jnsTrans, vd, versiModul, companyId;
    private Date pibTg, dokTupTg, ppjkTg, tgTiba;
    private BigInteger jkWaktu, jmCont, jmBrg;
    private BigDecimal ndpbm, nilInv, freight, bTambahan, diskon, asuransi, fob, cif, bruto, netto, nilVd;
    private boolean lengkap;

}

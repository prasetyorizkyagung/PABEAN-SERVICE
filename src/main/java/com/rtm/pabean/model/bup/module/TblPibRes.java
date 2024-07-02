package com.rtm.pabean.model.bup.module;

import java.math.BigInteger;
import java.util.Date;

import lombok.Data;

@Data
public class TblPibRes {

    private String car, resKd, resWk, dokResNo, kpbc, pibNo, kdGudang, pejabat1, nip1, jabatan1, pejabat2, nip2, komWk, deskripsi, noKemas, npwpImp, namaImp, alamatImp, idPpjk,
            namaPpjk, alamatPpjk, kodeBill, tanggalBill, tanggalJtTempo, tanggalAju, totalBayar, terbilang;
    private Date resTg, dokResTg, pibTg, jatuhTempo, komTg;
    private BigInteger jmKemas;
    private boolean dibaca;
    
}

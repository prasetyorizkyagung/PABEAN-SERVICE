package com.rtm.pabean.dto.ceisa.document.bue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Barang {

    private int seriBarang;
    private BigInteger jumlahKemasan;
    private BigDecimal asuransi, cif, fob, freight, hargaPatokan, hargaSatuan, netto, volume,
            nilaiDanaSawit, cifRupiah, jumlahSatuan;
    private String merk, posTarif, spesifikasiLain, tipe, ukuran, uraian, kodeNegaraAsal, kodeDaerahAsal, kodeBarang,
            kodeSatuanBarang, kodeJenisKemasan;
    private List<BarangTarif> barangTarif;
    private List<BarangDokumen> barangDokumen;
    private List<BarangPemilik> barangPemilik;

    public List<BarangTarif> getBarangTarif() {
        if (this.barangTarif == null) {
            this.barangTarif = new ArrayList<>();
        }
        return this.barangTarif;
    }

    public List<BarangDokumen> getBarangDokumen() {
        if (this.barangDokumen == null) {
            this.barangDokumen = new ArrayList<>();
        }
        return this.barangDokumen;
    }

    public List<BarangPemilik> getBarangPemilik() {
        if (this.barangPemilik == null) {
            this.barangPemilik = new ArrayList<>();
        }
        return this.barangPemilik;
    }
}

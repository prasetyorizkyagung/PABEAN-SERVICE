package com.rtm.pabean.dto.ceisa.document.bup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Barang {

    private BigDecimal asuransi, cif, fob, freight, hargaSatuan, jumlahSatuan, netto, saldoAkhir, saldoAwal, cifRupiah;
    private String kodeJenisKemasan, kodeNegaraAsal, kodeSatuanBarang, merk, posTarif, pernyataanLartas, tipe, uraian,
            kodeKondisiBarang, ukuran, spesifikasiLain, kodeBarang;
    private int seriBarang, jumlahKemasan;
    private List<BarangVd> barangVd;
    private List<BarangTarif> barangTarif;
    private List<BarangDokumen> barangDokumen;
    private List<BarangSpekKhusus> barangSpekKhusus;

    public List<BarangSpekKhusus> getBarangSpekKhusus() {
        if (this.barangSpekKhusus == null) {
            this.barangSpekKhusus = new ArrayList<>();
        }
        return this.barangSpekKhusus;
    }

    public List<BarangVd> getBarangVd() {
        if (this.barangVd == null) {
            this.barangVd = new ArrayList<>();
        }
        return this.barangVd;
    }

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

    public BigDecimal getAsuransi() {
        return Optional.ofNullable(this.asuransi).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getCif() {
        return Optional.ofNullable(this.cif).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getFob() {
        return Optional.ofNullable(this.fob).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getFreight() {
        return Optional.ofNullable(this.freight).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getHargaSatuan() {
        return Optional.ofNullable(this.hargaSatuan).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getJumlahSatuan() {
        return Optional.ofNullable(this.jumlahSatuan).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getNetto() {
        return Optional.ofNullable(this.netto).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getSaldoAkhir() {
        return Optional.ofNullable(this.saldoAkhir).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getSaldoAwal() {
        return Optional.ofNullable(this.saldoAwal).orElse(BigDecimal.ZERO);
    }

    public BigDecimal getCifRupiah() {
        return Optional.ofNullable(this.cifRupiah).orElse(BigDecimal.ZERO);
    }

}

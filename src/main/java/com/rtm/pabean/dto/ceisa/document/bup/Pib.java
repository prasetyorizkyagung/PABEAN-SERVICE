package com.rtm.pabean.dto.ceisa.document.bup;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Pib {

    private String asalData, kodeJenisImpor, KodeJenisProsedur, flagVd, kodeAsuransi, kodeCaraBayar, kodeDokumen,
            kodeIncoterm, kodeJenisNilai, kodeKantor, kodePelMuat, kodePelTujuan, kodePelTransit, kodeTps, kodeValuta,
            nomorAju, tanggalTiba, jabatanTtd, kotaTtd, namaTtd, nomorBc11, posBc11, subposBc11, kodeTutupPu,
            tanggalTtd, tanggalBc11;
    private BigDecimal asuransi, bruto, cif, vd, freight, ndpbm, netto, biayaTambahan, biayaPengurang;
    private List<Barang> barang;
    private List<Entitas> entitas;
    private List<Kemasan> kemasan;
    private List<Kontainer> kontainer;
    private List<Dokumen> dokumen;
    private List<Pengangkut> pengangkut;
    private int jumlahKontainer;

    @JsonInclude(Include.NON_EMPTY)
    public List<Barang> getBarang() {
        if (this.barang == null) {
            this.barang = new ArrayList<>();
        }
        return this.barang;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Entitas> getEntitas() {
        if (this.entitas == null) {
            this.entitas = new ArrayList<>();
        }
        return this.entitas;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Kemasan> getKemasan() {
        if (this.kemasan == null) {
            this.kemasan = new ArrayList<>();
        }
        return this.kemasan;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Kontainer> getKontainer() {
        if (this.kontainer == null) {
            this.kontainer = new ArrayList<>();
        }
        return this.kontainer;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Dokumen> getDokumen() {
        if (this.dokumen == null) {
            this.dokumen = new ArrayList<>();
        }
        return this.dokumen;
    }

    @JsonInclude(Include.NON_EMPTY)
    public List<Pengangkut> getPengangkut() {
        if (this.pengangkut == null) {
            this.pengangkut = new ArrayList<>();
        }
        return this.pengangkut;
    }

    @JsonInclude(Include.NON_EMPTY)
    public String getTanggalTiba() {
        return this.tanggalTiba;
    }

    @JsonInclude(Include.NON_EMPTY)
    public String getTanggalTtd() {
        return this.tanggalTtd;
    }

    @JsonInclude(Include.NON_EMPTY)
    public String getTanggalBc11() {
        return this.tanggalBc11;
    }
}

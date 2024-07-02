package com.rtm.pabean.dto.ceisa.document.bue;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class Peb {

    private String asalData, kodeAsuransi, kodeCaraBayar, kodeJenisEkspor, kodeKantor, kodeKategoriEkspor,
            kodePelBongkar, kodePelMuat, kodePelTujuan, kodeValuta, nomorAju, kodeDokumen, flagCurah, flagMigas,
            jabatanTtd, kotaTtd, namaTtd, tanggalTtd, kodeCaraDagang, tanggalEkspor, kodeKantorPeriksa, kodePembayar,
            kodeKantorMuat, kodeTps, tanggalPeriksa, kodeLokasi, kodePelEkspor, kodeKantorEkspor, kodeIncoterm, kodeNegaraTujuan;
    private BigDecimal asuransi, bruto, fob, freight, ndpbm, netto, nilaiMaklon;
    private List<Barang> barang;
    private List<Entitas> entitas;
    private List<Kemasan> kemasan;
    private List<Kontainer> kontainer;
    private List<Dokumen> dokumen;
    private List<Pengangkut> pengangkut;
    private List<BankDevisa> bankDevisa;
    private List<KesiapanBarang> kesiapanBarang;
    private int jumlahKontainer;

    public List<Barang> getBarang() {
        if (this.barang == null) {
            this.barang = new ArrayList<>();
        }
        return this.barang;
    }

    public List<Entitas> getEntitas() {
        if (this.entitas == null) {
            this.entitas = new ArrayList<>();
        }
        return this.entitas;
    }

    public List<Kemasan> getKemasan() {
        if (this.kemasan == null) {
            this.kemasan = new ArrayList<>();
        }
        return this.kemasan;
    }

    public List<Kontainer> getKontainer() {
        if (this.kontainer == null) {
            this.kontainer = new ArrayList<>();
        }
        return this.kontainer;
    }

    public List<Dokumen> getDokumen() {
        if (this.dokumen == null) {
            this.dokumen = new ArrayList<>();
        }
        return this.dokumen;
    }

    public List<Pengangkut> getPengangkut() {
        if (this.pengangkut == null) {
            this.pengangkut = new ArrayList<>();
        }
        return this.pengangkut;
    }

    public List<BankDevisa> getBankDevisa() {
        if (this.bankDevisa == null) {
            this.bankDevisa = new ArrayList<>();
        }
        return this.bankDevisa;
    }

    public List<KesiapanBarang> getKesiapanBarang() {
        if (this.kesiapanBarang == null) {
            this.kesiapanBarang = new ArrayList<>();
        }
        return this.kesiapanBarang;
    }
}

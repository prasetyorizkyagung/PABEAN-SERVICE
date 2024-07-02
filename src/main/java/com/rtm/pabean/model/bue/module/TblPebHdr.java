package com.rtm.pabean.model.bue.module;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

public class TblPebHdr {

    private String car, jnEks, katEks, jnPeb, jnEksOld, jnLain, jnDag, jnByr, idEks, npwpEks, namaEks, almtEks, noSiup,
            jnPerus, noTdp, niper, statusH, idQq, npwpQq, namaQq, AlmtQq, niperQq, namaBeli, almtBeli, negBeli,
            idPpjk, npwpPpjk, namaPpjk, almtPpjk, noPpjk, moda, carrier, voy, noShipOrd, pelMuat, pelBongkar,
            pelTransit, noInv, noLpse, propBrg, negTuju, delivery, kdVal, kdHrg, merk, noDaft, kdKtr, ktrGate,
            kdBank, lokBank, noSsp, jnTpb, lokTpb, kdLokBrg, kdKtrMuat, kdKtrPriks, wkSiap, asalData, tpName, jnPartOf,
            bendera, ttdPeb, status, snrf, noBcf, nomorEdi, kmdt, kdHanggar, kons, pelMuatEks, noBc11, posBc11,
            subPosBc11, kdKtrEks, pelTujuan, namaBeli2, almtBeli2, negBeli2, versiModul, urCaraBayar, gudangPlb,
            pernyataan, flKdVal, flFob, flNilMaklon, curah, kdAss;
    private BigInteger jmCont, jmBrg, jmBrgCth, jmBrgGab, jmBrgPjt, seriBcf, jmlKemPeb, nilPs, jmlKmsPeb;
    private BigDecimal nilInv, freight, asuransi, fob, bruto, netto, nilPe, volume, nilKurs, pnbp, nilMaklon,
            nilPajakLain, kurs;
    private Date tgSiup, tgTdp, tgPpjk, tgEks, tgShipOrd, tgInv, tgLpse, tgDaft, tgSsp, tgSiap, tgBcf, tgBc11,
            tglBerlakuTod;
    private Boolean flSie, flKarantina, flSm, flIzLain, pebOk, lengkap;

    public BigInteger getJmlKmsPeb() {
        return jmlKmsPeb;
    }

    public void setJmlKmsPeb(BigInteger jmlKmsPeb) {
        this.jmlKmsPeb = jmlKmsPeb;
    }

    public String getKdAss() {
        return kdAss;
    }

    public void setKdAss(String kdAss) {
        this.kdAss = kdAss;
    }

    public String getCar() {
        return car;
    }

    public void setCar(String car) {
        this.car = car;
    }

    public String getJnEks() {
        return jnEks;
    }

    public void setJnEks(String jnEks) {
        this.jnEks = jnEks;
    }

    public String getKatEks() {
        return katEks;
    }

    public void setKatEks(String katEks) {
        this.katEks = katEks;
    }

    public String getJnPeb() {
        return jnPeb;
    }

    public void setJnPeb(String jnPeb) {
        this.jnPeb = jnPeb;
    }

    public String getJnEksOld() {
        return jnEksOld;
    }

    public void setJnEksOld(String jnEksOld) {
        this.jnEksOld = jnEksOld;
    }

    public String getJnLain() {
        return jnLain;
    }

    public void setJnLain(String jnLain) {
        this.jnLain = jnLain;
    }

    public String getJnDag() {
        return jnDag;
    }

    public void setJnDag(String jnDag) {
        this.jnDag = jnDag;
    }

    public String getJnByr() {
        return jnByr;
    }

    public void setJnByr(String jnByr) {
        this.jnByr = jnByr;
    }

    public String getIdEks() {
        return idEks;
    }

    public void setIdEks(String idEks) {
        this.idEks = idEks;
    }

    public String getNpwpEks() {
        return npwpEks;
    }

    public void setNpwpEks(String npwpEks) {
        this.npwpEks = npwpEks;
    }

    public String getNamaEks() {
        return namaEks;
    }

    public void setNamaEks(String namaEks) {
        this.namaEks = namaEks;
    }

    public String getAlmtEks() {
        return almtEks;
    }

    public void setAlmtEks(String almtEks) {
        this.almtEks = almtEks;
    }

    public String getNoSiup() {
        return noSiup;
    }

    public void setNoSiup(String noSiup) {
        this.noSiup = noSiup;
    }

    public String getJnPerus() {
        return jnPerus;
    }

    public void setJnPerus(String jnPerus) {
        this.jnPerus = jnPerus;
    }

    public String getNoTdp() {
        return noTdp;
    }

    public void setNoTdp(String noTdp) {
        this.noTdp = noTdp;
    }

    public String getNiper() {
        return niper;
    }

    public void setNiper(String niper) {
        this.niper = niper;
    }

    public String getStatusH() {
        return statusH;
    }

    public void setStatusH(String statusH) {
        this.statusH = statusH;
    }

    public String getIdQq() {
        return idQq;
    }

    public void setIdQq(String idQq) {
        this.idQq = idQq;
    }

    public String getNpwpQq() {
        return npwpQq;
    }

    public void setNpwpQq(String npwpQq) {
        this.npwpQq = npwpQq;
    }

    public String getNamaQq() {
        return namaQq;
    }

    public void setNamaQq(String namaQq) {
        this.namaQq = namaQq;
    }

    public String getAlmtQq() {
        return AlmtQq;
    }

    public void setAlmtQq(String AlmtQq) {
        this.AlmtQq = AlmtQq;
    }

    public String getNiperQq() {
        return niperQq;
    }

    public void setNiperQq(String niperQq) {
        this.niperQq = niperQq;
    }

    public String getNamaBeli() {
        return namaBeli;
    }

    public void setNamaBeli(String namaBeli) {
        this.namaBeli = namaBeli;
    }

    public String getAlmtBeli() {
        return almtBeli;
    }

    public void setAlmtBeli(String almtBeli) {
        this.almtBeli = almtBeli;
    }

    public String getNegBeli() {
        return negBeli;
    }

    public void setNegBeli(String negBeli) {
        this.negBeli = negBeli;
    }

    public String getIdPpjk() {
        return idPpjk;
    }

    public void setIdPpjk(String idPpjk) {
        this.idPpjk = idPpjk;
    }

    public String getNpwpPpjk() {
        return npwpPpjk;
    }

    public void setNpwpPpjk(String npwpPpjk) {
        this.npwpPpjk = npwpPpjk;
    }

    public String getNamaPpjk() {
        return namaPpjk;
    }

    public void setNamaPpjk(String namaPpjk) {
        this.namaPpjk = namaPpjk;
    }

    public String getAlmtPpjk() {
        return almtPpjk;
    }

    public void setAlmtPpjk(String almtPpjk) {
        this.almtPpjk = almtPpjk;
    }

    public String getNoPpjk() {
        return noPpjk;
    }

    public void setNoPpjk(String noPpjk) {
        this.noPpjk = noPpjk;
    }

    public String getModa() {
        return moda;
    }

    public void setModa(String moda) {
        this.moda = moda;
    }

    public String getCarrier() {
        return carrier;
    }

    public void setCarrier(String carrier) {
        this.carrier = carrier;
    }

    public String getVoy() {
        return voy;
    }

    public void setVoy(String voy) {
        this.voy = voy;
    }

    public String getNoShipOrd() {
        return noShipOrd;
    }

    public void setNoShipOrd(String noShipOrd) {
        this.noShipOrd = noShipOrd;
    }

    public String getPelMuat() {
        return pelMuat;
    }

    public void setPelMuat(String pelMuat) {
        this.pelMuat = pelMuat;
    }

    public String getPelBongkar() {
        return pelBongkar;
    }

    public void setPelBongkar(String pelBongkar) {
        this.pelBongkar = pelBongkar;
    }

    public String getPelTransit() {
        return pelTransit;
    }

    public void setPelTransit(String pelTransit) {
        this.pelTransit = pelTransit;
    }

    public String getNoInv() {
        return noInv;
    }

    public void setNoInv(String noInv) {
        this.noInv = noInv;
    }

    public String getNoLpse() {
        return noLpse;
    }

    public void setNoLpse(String noLpse) {
        this.noLpse = noLpse;
    }

    public String getPropBrg() {
        return propBrg;
    }

    public void setPropBrg(String propBrg) {
        this.propBrg = propBrg;
    }

    public String getNegTuju() {
        return negTuju;
    }

    public void setNegTuju(String negTuju) {
        this.negTuju = negTuju;
    }

    public String getDelivery() {
        return delivery;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public String getKdVal() {
        return kdVal;
    }

    public void setKdVal(String kdVal) {
        this.kdVal = kdVal;
    }

    public String getKdHrg() {
        return kdHrg;
    }

    public void setKdHrg(String kdHrg) {
        this.kdHrg = kdHrg;
    }

    public String getMerk() {
        return merk;
    }

    public void setMerk(String merk) {
        this.merk = merk;
    }

    public String getNoDaft() {
        return noDaft;
    }

    public void setNoDaft(String noDaft) {
        this.noDaft = noDaft;
    }

    public String getKdKtr() {
        return kdKtr;
    }

    public void setKdKtr(String kdKtr) {
        this.kdKtr = kdKtr;
    }

    public String getKtrGate() {
        return ktrGate;
    }

    public void setKtrGate(String ktrGate) {
        this.ktrGate = ktrGate;
    }

    public String getKdBank() {
        return kdBank;
    }

    public void setKdBank(String kdBank) {
        this.kdBank = kdBank;
    }

    public String getLokBank() {
        return lokBank;
    }

    public void setLokBank(String lokBank) {
        this.lokBank = lokBank;
    }

    public String getNoSsp() {
        return noSsp;
    }

    public void setNoSsp(String noSsp) {
        this.noSsp = noSsp;
    }

    public String getJnTpb() {
        return jnTpb;
    }

    public void setJnTpb(String jnTpb) {
        this.jnTpb = jnTpb;
    }

    public String getLokTpb() {
        return lokTpb;
    }

    public void setLokTpb(String lokTpb) {
        this.lokTpb = lokTpb;
    }

    public String getKdLokBrg() {
        return kdLokBrg;
    }

    public void setKdLokBrg(String kdLokBrg) {
        this.kdLokBrg = kdLokBrg;
    }

    public String getKdKtrMuat() {
        return kdKtrMuat;
    }

    public void setKdKtrMuat(String kdKtrMuat) {
        this.kdKtrMuat = kdKtrMuat;
    }

    public String getKdKtrPriks() {
        return kdKtrPriks;
    }

    public void setKdKtrPriks(String kdKtrPriks) {
        this.kdKtrPriks = kdKtrPriks;
    }

    public String getWkSiap() {
        return wkSiap;
    }

    public void setWkSiap(String wkSiap) {
        this.wkSiap = wkSiap;
    }

    public String getAsalData() {
        return asalData;
    }

    public void setAsalData(String asalData) {
        this.asalData = asalData;
    }

    public String getTpName() {
        return tpName;
    }

    public void setTpName(String tpName) {
        this.tpName = tpName;
    }

    public String getJnPartOf() {
        return jnPartOf;
    }

    public void setJnPartOf(String jnPartOf) {
        this.jnPartOf = jnPartOf;
    }

    public String getBendera() {
        return bendera;
    }

    public void setBendera(String bendera) {
        this.bendera = bendera;
    }

    public String getTtdPeb() {
        return ttdPeb;
    }

    public void setTtdPeb(String ttdPeb) {
        this.ttdPeb = ttdPeb;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSnrf() {
        return snrf;
    }

    public void setSnrf(String snrf) {
        this.snrf = snrf;
    }

    public String getNoBcf() {
        return noBcf;
    }

    public void setNoBcf(String noBcf) {
        this.noBcf = noBcf;
    }

    public String getNomorEdi() {
        return nomorEdi;
    }

    public void setNomorEdi(String nomorEdi) {
        this.nomorEdi = nomorEdi;
    }

    public String getKmdt() {
        return kmdt;
    }

    public void setKmdt(String kmdt) {
        this.kmdt = kmdt;
    }

    public String getKdHanggar() {
        return kdHanggar;
    }

    public void setKdHanggar(String kdHanggar) {
        this.kdHanggar = kdHanggar;
    }

    public String getKons() {
        return kons;
    }

    public void setKons(String kons) {
        this.kons = kons;
    }

    public String getPelMuatEks() {
        return pelMuatEks;
    }

    public void setPelMuatEks(String pelMuatEks) {
        this.pelMuatEks = pelMuatEks;
    }

    public String getNoBc11() {
        return noBc11;
    }

    public void setNoBc11(String noBc11) {
        this.noBc11 = noBc11;
    }

    public String getPosBc11() {
        return posBc11;
    }

    public void setPosBc11(String posBc11) {
        this.posBc11 = posBc11;
    }

    public String getSubPosBc11() {
        return subPosBc11;
    }

    public void setSubPosBc11(String subPosBc11) {
        this.subPosBc11 = subPosBc11;
    }

    public String getKdKtrEks() {
        return kdKtrEks;
    }

    public void setKdKtrEks(String kdKtrEks) {
        this.kdKtrEks = kdKtrEks;
    }

    public String getPelTujuan() {
        return pelTujuan;
    }

    public void setPelTujuan(String pelTujuan) {
        this.pelTujuan = pelTujuan;
    }

    public String getNamaBeli2() {
        return namaBeli2;
    }

    public void setNamaBeli2(String namaBeli2) {
        this.namaBeli2 = namaBeli2;
    }

    public String getAlmtBeli2() {
        return almtBeli2;
    }

    public void setAlmtBeli2(String almtBeli2) {
        this.almtBeli2 = almtBeli2;
    }

    public String getNegBeli2() {
        return negBeli2;
    }

    public void setNegBeli2(String negBeli2) {
        this.negBeli2 = negBeli2;
    }

    public String getVersiModul() {
        return versiModul;
    }

    public void setVersiModul(String versiModul) {
        this.versiModul = versiModul;
    }

    public String getUrCaraBayar() {
        return urCaraBayar;
    }

    public void setUrCaraBayar(String urCaraBayar) {
        this.urCaraBayar = urCaraBayar;
    }

    public String getGudangPlb() {
        return gudangPlb;
    }

    public void setGudangPlb(String gudangPlb) {
        this.gudangPlb = gudangPlb;
    }

    public String getPernyataan() {
        return pernyataan;
    }

    public void setPernyataan(String pernyataan) {
        this.pernyataan = pernyataan;
    }

    public String getFlKdVal() {
        return flKdVal;
    }

    public void setFlKdVal(String flKdVal) {
        this.flKdVal = flKdVal;
    }

    public String getFlFob() {
        return flFob;
    }

    public void setFlFob(String flFob) {
        this.flFob = flFob;
    }

    public String getFlNilMaklon() {
        return flNilMaklon;
    }

    public void setFlNilMaklon(String flNilMaklon) {
        this.flNilMaklon = flNilMaklon;
    }

    public String getCurah() {
        return curah;
    }

    public void setCurah(String curah) {
        this.curah = curah;
    }

    public BigInteger getJmCont() {
        return jmCont;
    }

    public void setJmCont(BigInteger jmCont) {
        this.jmCont = jmCont;
    }

    public BigInteger getJmBrg() {
        return jmBrg;
    }

    public void setJmBrg(BigInteger jmBrg) {
        this.jmBrg = jmBrg;
    }

    public BigInteger getJmBrgCth() {
        return jmBrgCth;
    }

    public void setJmBrgCth(BigInteger jmBrgCth) {
        this.jmBrgCth = jmBrgCth;
    }

    public BigInteger getJmBrgGab() {
        return jmBrgGab;
    }

    public void setJmBrgGab(BigInteger jmBrgGab) {
        this.jmBrgGab = jmBrgGab;
    }

    public BigInteger getJmBrgPjt() {
        return jmBrgPjt;
    }

    public void setJmBrgPjt(BigInteger jmBrgPjt) {
        this.jmBrgPjt = jmBrgPjt;
    }

    public BigInteger getSeriBcf() {
        return seriBcf;
    }

    public void setSeriBcf(BigInteger seriBcf) {
        this.seriBcf = seriBcf;
    }

    public BigInteger getJmlKemPeb() {
        return jmlKemPeb;
    }

    public void setJmlKemPeb(BigInteger jmlKemPeb) {
        this.jmlKemPeb = jmlKemPeb;
    }

    public BigInteger getNilPs() {
        return nilPs;
    }

    public void setNilPs(BigInteger nilPs) {
        this.nilPs = nilPs;
    }

    public BigDecimal getNilInv() {
        return nilInv;
    }

    public void setNilInv(BigDecimal nilInv) {
        this.nilInv = nilInv;
    }

    public BigDecimal getFreight() {
        return freight;
    }

    public void setFreight(BigDecimal freight) {
        this.freight = freight;
    }

    public BigDecimal getAsuransi() {
        return asuransi;
    }

    public void setAsuransi(BigDecimal asuransi) {
        this.asuransi = asuransi;
    }

    public BigDecimal getFob() {
        return Optional.ofNullable(fob).orElse(BigDecimal.ZERO);
    }

    public void setFob(BigDecimal fob) {
        this.fob = fob;
    }

    public BigDecimal getBruto() {
        return bruto;
    }

    public void setBruto(BigDecimal bruto) {
        this.bruto = bruto;
    }

    public BigDecimal getNetto() {
        return netto;
    }

    public void setNetto(BigDecimal netto) {
        this.netto = netto;
    }

    public BigDecimal getNilPe() {
        return nilPe;
    }

    public void setNilPe(BigDecimal nilPe) {
        this.nilPe = nilPe;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getNilKurs() {
        return nilKurs;
    }

    public void setNilKurs(BigDecimal nilKurs) {
        this.nilKurs = nilKurs;
    }

    public BigDecimal getPnbp() {
        return pnbp;
    }

    public void setPnbp(BigDecimal pnbp) {
        this.pnbp = pnbp;
    }

    public BigDecimal getNilMaklon() {
        return nilMaklon;
    }

    public void setNilMaklon(BigDecimal nilMaklon) {
        this.nilMaklon = nilMaklon;
    }

    public BigDecimal getNilPajakLain() {
        return nilPajakLain;
    }

    public void setNilPajakLain(BigDecimal nilPajakLain) {
        this.nilPajakLain = nilPajakLain;
    }

    public BigDecimal getKurs() {
        return kurs;
    }

    public void setKurs(BigDecimal kurs) {
        this.kurs = kurs;
    }

    public Date getTgSiup() {
        return tgSiup;
    }

    public void setTgSiup(Date tgSiup) {
        this.tgSiup = tgSiup;
    }

    public Date getTgTdp() {
        return tgTdp;
    }

    public void setTgTdp(Date tgTdp) {
        this.tgTdp = tgTdp;
    }

    public Date getTgPpjk() {
        return tgPpjk;
    }

    public void setTgPpjk(Date tgPpjk) {
        this.tgPpjk = tgPpjk;
    }

    public Date getTgEks() {
        return tgEks;
    }

    public void setTgEks(Date tgEks) {
        this.tgEks = tgEks;
    }

    public Date getTgShipOrd() {
        return tgShipOrd;
    }

    public void setTgShipOrd(Date tgShipOrd) {
        this.tgShipOrd = tgShipOrd;
    }

    public Date getTgInv() {
        return tgInv;
    }

    public void setTgInv(Date tgInv) {
        this.tgInv = tgInv;
    }

    public Date getTgLpse() {
        return tgLpse;
    }

    public void setTgLpse(Date tgLpse) {
        this.tgLpse = tgLpse;
    }

    public Date getTgDaft() {
        return tgDaft;
    }

    public void setTgDaft(Date tgDaft) {
        this.tgDaft = tgDaft;
    }

    public Date getTgSsp() {
        return tgSsp;
    }

    public void setTgSsp(Date tgSsp) {
        this.tgSsp = tgSsp;
    }

    public Date getTgSiap() {
        return tgSiap;
    }

    public void setTgSiap(Date tgSiap) {
        this.tgSiap = tgSiap;
    }

    public Date getTgBcf() {
        return tgBcf;
    }

    public void setTgBcf(Date tgBcf) {
        this.tgBcf = tgBcf;
    }

    public Date getTgBc11() {
        return tgBc11;
    }

    public void setTgBc11(Date tgBc11) {
        this.tgBc11 = tgBc11;
    }

    public Date getTglBerlakuTod() {
        return tglBerlakuTod;
    }

    public void setTglBerlakuTod(Date tglBerlakuTod) {
        this.tglBerlakuTod = tglBerlakuTod;
    }

    public Boolean getFlSie() {
        return flSie == null ? false : flSie;
    }

    public void setFlSie(Boolean flSie) {
        this.flSie = flSie;
    }

    public Boolean getFlKarantina() {
        return flKarantina == null ? false : flKarantina;
    }

    public void setFlKarantina(Boolean flKarantina) {
        this.flKarantina = flKarantina;
    }

    public Boolean getFlSm() {
        return flSm == null ? false : flSm;
    }

    public void setFlSm(Boolean flSm) {
        this.flSm = flSm;
    }

    public Boolean getFlIzLain() {
        return flIzLain == null ? false : flIzLain;
    }

    public void setFlIzLain(Boolean flIzLain) {
        this.flIzLain = flIzLain;
    }

    public Boolean getPebOk() {
        return pebOk == null ? false : pebOk;
    }

    public void setPebOk(Boolean pebOk) {
        this.pebOk = pebOk;
    }

    public Boolean getLengkap() {
        return lengkap == null ? false : lengkap;
    }

    public void setLengkap(Boolean lengkap) {
        this.lengkap = lengkap;
    }

}

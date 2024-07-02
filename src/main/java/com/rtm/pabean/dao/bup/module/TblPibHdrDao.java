package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibHdr;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibHdrDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public TblPibHdr getAll() throws IOException {
        TblPibHdr tblPibHdr = new TblPibHdr();
        Table pibHdr = jackcess.getTable(targetDatabase, "tblPibHdr");
        for (Row row : pibHdr) {
            tblPibHdr.setCar(row.getString("CAR"));
            tblPibHdr.setKdKpbc(row.getString("KdKpbc"));
            tblPibHdr.setPibNo(row.getString("PibNo"));
            tblPibHdr.setPibTg(DateUtil.toDate(row.getLocalDateTime("PibTg")));
            tblPibHdr.setJnPib(row.getString("JnPib"));
            tblPibHdr.setJnImp(row.getString("JnImp"));
            tblPibHdr.setJkWaktu(NumberUtil.toBigInteger(row.getShort("JkWaktu")));
            tblPibHdr.setCrByr(row.getString("CrByr"));
            tblPibHdr.setDokTupKd(row.getString("DokTupKd"));
            tblPibHdr.setDokTupNo(row.getString("DokTupNo"));
            tblPibHdr.setDokTupTg(DateUtil.toDate(row.getLocalDateTime("DokTupTg")));
            tblPibHdr.setPosNo(row.getString("PosNo"));
            tblPibHdr.setPosSub(row.getString("PosSub"));
            tblPibHdr.setPosSubSub(row.getString("PosSubSub"));
            tblPibHdr.setImpId(row.getString("ImpId"));
            tblPibHdr.setImpNpwp(row.getString("ImpNpwp"));
            tblPibHdr.setImpNama(row.getString("ImpNama"));
            tblPibHdr.setImpAlmt(row.getString("ImpAlmt"));
            tblPibHdr.setImpStatus(row.getString("ImpStatus"));
            tblPibHdr.setApiKd(row.getString("ApiKd"));
            tblPibHdr.setApiNo(row.getString("ApiNo"));
            tblPibHdr.setPpjkId(row.getString("PpjkId"));
            tblPibHdr.setPpjkNpwp(row.getString("PpjkNpwp"));
            tblPibHdr.setPpjkNama(row.getString("PpjkNama"));
            tblPibHdr.setPpjkAlmt(row.getString("PpjkAlmt"));
            tblPibHdr.setPpjkNo(row.getString("PpjkNo"));
            tblPibHdr.setPpjkTg(DateUtil.toDate(row.getLocalDateTime("PpjkTg")));
            tblPibHdr.setIndId(row.getString("IndId"));
            tblPibHdr.setIndNpwp(row.getString("IndNpwp"));
            tblPibHdr.setIndNama(row.getString("IndNama"));
            tblPibHdr.setIndAlmt(row.getString("IndAlmt"));
            tblPibHdr.setPasokNama(row.getString("PasokNama"));
            tblPibHdr.setPasokAlmt(row.getString("PasokAlmt"));
            tblPibHdr.setPasokNeg(row.getString("PasokNeg"));
            tblPibHdr.setPelBkr(row.getString("PelBkr"));
            tblPibHdr.setPelMuat(row.getString("PelMuat"));
            tblPibHdr.setPelTransit(row.getString("PelTransit"));
            tblPibHdr.setTmpTbn(row.getString("TmpTbn"));
            tblPibHdr.setModa(row.getString("Moda"));
            tblPibHdr.setAngkutNama(row.getString("AngkutNama"));
            tblPibHdr.setAngkutNo(row.getString("AngkutNo"));
            tblPibHdr.setAngkutFl(row.getString("AngkutFl"));
            tblPibHdr.setTgTiba(DateUtil.toDate(row.getLocalDateTime("TgTiba")));
            tblPibHdr.setKdVal(row.getString("KdVal"));
            tblPibHdr.setNdpbm(NumberUtil.toBigDecimal(row.getDouble("Ndpbm")));
            tblPibHdr.setNilInv(NumberUtil.toBigDecimal(row.getDouble("NilInv")));
            tblPibHdr.setFreight(NumberUtil.toBigDecimal(row.getDouble("Freight")));
            tblPibHdr.setBTambahan(NumberUtil.toBigDecimal(row.getDouble("BTambahan")));
            tblPibHdr.setDiskon(NumberUtil.toBigDecimal(row.getDouble("Diskon")));
            tblPibHdr.setKdAss(row.getString("KdAss"));
            tblPibHdr.setAsuransi(NumberUtil.toBigDecimal(row.getDouble("Asuransi")));
            tblPibHdr.setKdHrg(row.getString("KdHrg"));
            tblPibHdr.setFob(NumberUtil.toBigDecimal(row.getDouble("Fob")));
            tblPibHdr.setCif(NumberUtil.toBigDecimal(row.getDouble("Cif")));
            tblPibHdr.setBruto(NumberUtil.toBigDecimal(row.getDouble("Bruto")));
            tblPibHdr.setNetto(NumberUtil.toBigDecimal(row.getDouble("Netto")));
            tblPibHdr.setJmCont(NumberUtil.toBigInteger(row.getShort("JmCont")));
            tblPibHdr.setJmBrg(NumberUtil.toBigInteger(row.getShort("JmBrg")));
            tblPibHdr.setStatus(row.getString("Status"));
            tblPibHdr.setSnrf(row.getString("Snrf"));
            tblPibHdr.setKdFas(row.getString("KdFas"));
            tblPibHdr.setLengkap(row.getBoolean("Lengkap"));
            tblPibHdr.setBillNpwp(row.getString("BillNpwp"));
            tblPibHdr.setBillNama(row.getString("BillNama"));
            tblPibHdr.setBillAlmt(row.getString("BillAlmt"));
            tblPibHdr.setPenjualNama(row.getString("PenjualNama"));
            tblPibHdr.setPenjualAlmt(row.getString("PenjualAlmt"));
            tblPibHdr.setPenjualNeg(row.getString("PenjualNeg"));
            tblPibHdr.setPernyataan(row.getString("Pernyataan"));
            tblPibHdr.setJnsTrans(row.getString("JnsTrans"));
            tblPibHdr.setVd(row.getString("VD"));
            tblPibHdr.setVersiModul(row.getInt("VersiModul") == null ? "" : row.getInt("VersiModul").toString());
            tblPibHdr.setNilVd(NumberUtil.toBigDecimal(row.getDouble("NilVd")));
        }
        pibHdr.getDatabase().close();
        return tblPibHdr;
    }

    public TblPibHdr save(TblPibHdr tblPibHdr) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPibHdr");
        Map<String, Object> map = new HashMap<>();
        map.put("CAR", tblPibHdr.getCar());
        map.put("KdKpbc", tblPibHdr.getKdKpbc());
        map.put("PibNo", tblPibHdr.getPibNo());
        map.put("PibTg", tblPibHdr.getPibTg());
        map.put("JnPib", tblPibHdr.getJnPib());
        map.put("JnImp", tblPibHdr.getJnImp());
        map.put("JkWaktu", tblPibHdr.getJkWaktu());
        map.put("CrByr", tblPibHdr.getCrByr());
        map.put("DokTupKd", tblPibHdr.getDokTupKd());
        map.put("DokTupNo", tblPibHdr.getDokTupNo());
        map.put("DokTupTg", tblPibHdr.getDokTupTg());
        map.put("PosNo", tblPibHdr.getPosNo());
        map.put("PosSub", tblPibHdr.getPosSub());
        map.put("PosSubSub", tblPibHdr.getPosSubSub());
        map.put("ImpId", tblPibHdr.getImpId());
        map.put("ImpNpwp", tblPibHdr.getImpNpwp());
        map.put("ImpNama", tblPibHdr.getImpNama());
        map.put("ImpAlmt", tblPibHdr.getImpAlmt());
        map.put("ImpStatus", tblPibHdr.getImpStatus());
        map.put("ApiKd", tblPibHdr.getApiKd());
        map.put("ApiNo", tblPibHdr.getApiNo());
        map.put("PpjkId", tblPibHdr.getPpjkId());
        map.put("PpjkNpwp", tblPibHdr.getPpjkNpwp());
        map.put("PpjkNama", tblPibHdr.getPpjkNama());
        map.put("PpjkAlmt", tblPibHdr.getPpjkAlmt());
        map.put("PpjkNo", tblPibHdr.getPpjkNo());
        map.put("PpjkTg", tblPibHdr.getPpjkTg());
        map.put("IndId", tblPibHdr.getIndId());
        map.put("IndNpwp", tblPibHdr.getIndNpwp());
        map.put("IndNama", tblPibHdr.getIndNama());
        map.put("IndAlmt", tblPibHdr.getIndAlmt());
        map.put("PasokNama", tblPibHdr.getPasokNama());
        map.put("PasokAlmt", tblPibHdr.getPasokAlmt());
        map.put("PasokNeg", tblPibHdr.getPasokNeg());
        map.put("PelBkr", tblPibHdr.getPelBkr());
        map.put("PelMuat", tblPibHdr.getPelMuat());
        map.put("PelTransit", tblPibHdr.getPelTransit());
        map.put("TmpTbn", tblPibHdr.getTmpTbn());
        map.put("Moda", tblPibHdr.getModa());
        map.put("AngkutNama", tblPibHdr.getAngkutNama());
        map.put("AngkutNo", tblPibHdr.getAngkutNo());
        map.put("AngkutFl", tblPibHdr.getAngkutFl());
        map.put("TgTiba", tblPibHdr.getTgTiba());
        map.put("KdVal", tblPibHdr.getKdVal());
        map.put("Ndpbm", tblPibHdr.getNdpbm());
        map.put("NilInv", tblPibHdr.getNilInv());
        map.put("Freight", tblPibHdr.getFreight());
        map.put("BTambahan", tblPibHdr.getBTambahan());
        map.put("Diskon", tblPibHdr.getDiskon());
        map.put("KdAss", tblPibHdr.getKdAss());
        map.put("Asuransi", tblPibHdr.getAsuransi());
        map.put("KdHrg", tblPibHdr.getKdHrg());
        map.put("Fob", tblPibHdr.getFob());
        map.put("Cif", tblPibHdr.getCif());
        map.put("Bruto", tblPibHdr.getBruto());
        map.put("Netto", tblPibHdr.getNetto());
        map.put("JmCont", tblPibHdr.getJmCont());
        map.put("JmBrg", tblPibHdr.getJmBrg());
        map.put("Status", tblPibHdr.getStatus());
        map.put("Snrf", tblPibHdr.getSnrf());
        map.put("KdFas", tblPibHdr.getKdFas());
        map.put("Lengkap", tblPibHdr.isLengkap());
        map.put("BillNpwp", tblPibHdr.getBillNpwp());
        map.put("BillNama", tblPibHdr.getBillNama());
        map.put("BillAlmt", tblPibHdr.getBillAlmt());
        map.put("PenjualNama", tblPibHdr.getPenjualNama());
        map.put("PenjualAlmt", tblPibHdr.getPenjualAlmt());
        map.put("PenjualNeg", tblPibHdr.getPenjualNeg());
        map.put("Pernyataan", tblPibHdr.getPernyataan());
        map.put("JnsTrans", tblPibHdr.getJnsTrans());
        map.put("VD", tblPibHdr.getVd());
        map.put("VersiModul", tblPibHdr.getVersiModul());
        map.put("NilVd", tblPibHdr.getNilVd());
        jackcess.insertRow(table, map);
        return tblPibHdr;
    }
}

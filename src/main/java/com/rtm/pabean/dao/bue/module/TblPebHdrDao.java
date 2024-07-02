package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebHdr;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebHdrDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public TblPebHdr getAll() throws IOException {
        TblPebHdr tblPebHdr = new TblPebHdr();
        Table pebHdr = jackcess.getTable(targetDatabase, "tblPebHdr");
        for (Row row : pebHdr) {
            tblPebHdr.setKdKtr(row.getString("KDKTR"));
            tblPebHdr.setKdKtrEks(row.getString("KDKTREKS"));
            tblPebHdr.setJnEks(row.getString("JnEks"));
            tblPebHdr.setKatEks(row.getString("KatEks"));
            tblPebHdr.setJnDag(row.getString("JNDAG"));
            tblPebHdr.setJnByr(row.getString("JNBYR"));
            tblPebHdr.setUrCaraBayar(row.getString("URCARABAYAR"));
            tblPebHdr.setIdEks(row.getString("IDEKS"));
            tblPebHdr.setNpwpEks(row.getString("NPWPEKS"));
            tblPebHdr.setNamaEks(row.getString("NamaEks"));
            tblPebHdr.setAlmtEks(row.getString("AlmtEks"));
            tblPebHdr.setNiper(row.getString("Niper"));
            tblPebHdr.setStatusH(row.getString("StatusH"));
            tblPebHdr.setNoTdp(row.getString("NoTdp"));
            tblPebHdr.setTgTdp(DateUtil.toDate(row.getLocalDateTime("TgTdp")));
            tblPebHdr.setIdQq(row.getString("IdQq"));
            tblPebHdr.setNpwpQq(row.getString("NpwpQq"));
            tblPebHdr.setNamaQq(row.getString("NamaQq"));
            tblPebHdr.setAlmtQq(row.getString("AlmtQq"));
            tblPebHdr.setNamaBeli2(row.getString("NAMABELI2"));
            tblPebHdr.setAlmtBeli2(row.getString("ALMTBELI2"));
            tblPebHdr.setNegBeli2(row.getString("NEGBELI2"));
            tblPebHdr.setNamaBeli(row.getString("NAMABELI"));
            tblPebHdr.setAlmtBeli(row.getString("ALMTBELI"));
            tblPebHdr.setNegBeli(row.getString("NEGBELI"));
            tblPebHdr.setModa(row.getString("MODA"));
            tblPebHdr.setCarrier(row.getString("CARRIER"));
            tblPebHdr.setVoy(row.getString("VOY"));
            tblPebHdr.setBendera(row.getString("BENDERA"));
            tblPebHdr.setTgEks(DateUtil.toDate(row.getLocalDateTime("TGEKS")));
            tblPebHdr.setPelMuat(row.getString("PELMUAT"));
            tblPebHdr.setPelMuatEks(row.getString("PELMUATEKS"));
            tblPebHdr.setPelTujuan(row.getString("PELTUJUAN"));
            tblPebHdr.setPelBongkar(row.getString("PELBONGKAR"));
            tblPebHdr.setKdLokBrg(row.getString("KDLOKBRG"));
            tblPebHdr.setTgSiap(DateUtil.toDate(row.getLocalDateTime("TGSIAP")));
            tblPebHdr.setKdKtrPriks(row.getString("KDKTRPRIKS"));
            tblPebHdr.setGudangPlb(row.getString("GUDANGPLB"));
            tblPebHdr.setNegTuju(row.getString("NEGTUJU"));
            tblPebHdr.setDelivery(row.getString("DELIVERY"));
            tblPebHdr.setKdVal(row.getString("KDVAL"));
            tblPebHdr.setFreight(NumberUtil.toBigDecimal(row.getDouble("FREIGHT")));
            tblPebHdr.setNilMaklon(NumberUtil.toBigDecimal(row.getDouble("NILMAKLON")));
            tblPebHdr.setNilPajakLain(NumberUtil.toBigDecimal(row.getDouble("NILPAJAKLAIN")));
            tblPebHdr.setKdAss(row.getString("KdAss"));
            tblPebHdr.setAsuransi(NumberUtil.toBigDecimal(row.getDouble("ASURANSI")));
            tblPebHdr.setNilInv(NumberUtil.toBigDecimal(row.getDouble("NilInv")));
            tblPebHdr.setVolume(NumberUtil.toBigDecimal(row.getDouble("VOLUME")));
            tblPebHdr.setBruto(NumberUtil.toBigDecimal(row.getDouble("BRUTO")));
            tblPebHdr.setNetto(NumberUtil.toBigDecimal(row.getDouble("NETTO")));
            tblPebHdr.setKmdt(row.getString("KMDT"));
            tblPebHdr.setCurah(row.getString("Curah"));
            tblPebHdr.setCar(row.getString("CAR"));
            tblPebHdr.setIdPpjk(row.getString("IDPPJK"));
            tblPebHdr.setNpwpPpjk(row.getString("NPWPPPJK"));
            tblPebHdr.setNamaPpjk(row.getString("NamaPpjk"));
            tblPebHdr.setAlmtPpjk(row.getString("AlmtPpjk"));
            tblPebHdr.setFob(NumberUtil.toBigDecimal(row.getDouble("FOB")));
            tblPebHdr.setKtrGate(row.getString("KTRGATE"));
            tblPebHdr.setSeriBcf(NumberUtil.toBigInteger(row.getDouble("SERIBCF")));
            tblPebHdr.setTtdPeb(row.getString("TTDPEB"));
        }
        pebHdr.getDatabase().close();
        return tblPebHdr;
    }

    public void save(TblPebHdr tblPebHdr) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebHdr");
        Map<String, Object> mapTblPebHdr = new HashMap<>();
        mapTblPebHdr.put("KDKTR", tblPebHdr.getKdKtr());
        mapTblPebHdr.put("KDKTREKS", tblPebHdr.getKdKtrEks());
        mapTblPebHdr.put("JnEks", tblPebHdr.getJnEks());
        mapTblPebHdr.put("KatEks", tblPebHdr.getKatEks());
        mapTblPebHdr.put("JNDAG", tblPebHdr.getJnDag());
        mapTblPebHdr.put("JNBYR", tblPebHdr.getJnByr());
        mapTblPebHdr.put("URCARABAYAR", tblPebHdr.getUrCaraBayar());
        mapTblPebHdr.put("IDEKS", tblPebHdr.getIdEks());
        mapTblPebHdr.put("NPWPEKS", tblPebHdr.getNpwpEks());
        mapTblPebHdr.put("NamaEks", tblPebHdr.getNamaEks());
        mapTblPebHdr.put("AlmtEks", tblPebHdr.getAlmtEks());
        mapTblPebHdr.put("Niper", tblPebHdr.getNiper());
        mapTblPebHdr.put("StatusH", tblPebHdr.getStatusH());
        mapTblPebHdr.put("NoTdp", tblPebHdr.getNoTdp());
        mapTblPebHdr.put("TgTdp", tblPebHdr.getTgTdp());
        mapTblPebHdr.put("IdQq", tblPebHdr.getIdQq());
        mapTblPebHdr.put("NpwpQq", tblPebHdr.getNpwpQq());
        mapTblPebHdr.put("NamaQq", tblPebHdr.getNamaQq());
        mapTblPebHdr.put("AlmtQq", tblPebHdr.getAlmtQq());
        mapTblPebHdr.put("NAMABELI2", tblPebHdr.getNamaBeli2());
        mapTblPebHdr.put("ALMTBELI2", tblPebHdr.getAlmtBeli2());
        mapTblPebHdr.put("NEGBELI2", tblPebHdr.getNegBeli2());
        mapTblPebHdr.put("NAMABELI", tblPebHdr.getNamaBeli());
        mapTblPebHdr.put("ALMTBELI", tblPebHdr.getAlmtBeli());
        mapTblPebHdr.put("NEGBELI", tblPebHdr.getNegBeli());
        mapTblPebHdr.put("MODA", tblPebHdr.getModa());
        mapTblPebHdr.put("CARRIER", tblPebHdr.getCarrier());
        mapTblPebHdr.put("VOY", tblPebHdr.getVoy());
        mapTblPebHdr.put("BENDERA", tblPebHdr.getBendera());
        mapTblPebHdr.put("TGEKS", tblPebHdr.getTgEks());
        mapTblPebHdr.put("PELMUAT", tblPebHdr.getPelMuat());
        mapTblPebHdr.put("PELMUATEKS", tblPebHdr.getPelMuatEks());
        mapTblPebHdr.put("PELTUJUAN", tblPebHdr.getPelTujuan());
        mapTblPebHdr.put("PELBONGKAR", tblPebHdr.getPelBongkar());
        mapTblPebHdr.put("KDLOKBRG", tblPebHdr.getKdLokBrg());
        mapTblPebHdr.put("TGSIAP", tblPebHdr.getTgSiap());
        mapTblPebHdr.put("KDKTRPRIKS", tblPebHdr.getKdKtrPriks());
        mapTblPebHdr.put("GUDANGPLB", tblPebHdr.getGudangPlb());
        mapTblPebHdr.put("NEGTUJU", tblPebHdr.getNegTuju());
        mapTblPebHdr.put("DELIVERY", tblPebHdr.getDelivery());
        mapTblPebHdr.put("KDVAL", tblPebHdr.getKdVal());
        mapTblPebHdr.put("FREIGHT", tblPebHdr.getFreight());
        mapTblPebHdr.put("NILMAKLON", tblPebHdr.getNilMaklon());
        mapTblPebHdr.put("NILPAJAKLAIN", tblPebHdr.getNilPajakLain());
        mapTblPebHdr.put("KdAss", tblPebHdr.getKdAss());
        mapTblPebHdr.put("ASURANSI", tblPebHdr.getAsuransi());
        mapTblPebHdr.put("NilInv", tblPebHdr.getNilInv());
        mapTblPebHdr.put("VOLUME", tblPebHdr.getVolume());
        mapTblPebHdr.put("BRUTO", tblPebHdr.getBruto());
        mapTblPebHdr.put("NETTO", tblPebHdr.getNetto());
        mapTblPebHdr.put("KMDT", tblPebHdr.getKmdt());
        mapTblPebHdr.put("Curah", tblPebHdr.getCurah());
        mapTblPebHdr.put("CAR", tblPebHdr.getCar());
        mapTblPebHdr.put("IDPPJK", tblPebHdr.getIdPpjk());
        mapTblPebHdr.put("NPWPPPJK", tblPebHdr.getNpwpPpjk());
        mapTblPebHdr.put("NamaPpjk", tblPebHdr.getNamaPpjk());
        mapTblPebHdr.put("AlmtPpjk", tblPebHdr.getAlmtPpjk());
        mapTblPebHdr.put("FOB", tblPebHdr.getFob());
        mapTblPebHdr.put("KTRGATE", tblPebHdr.getKtrGate());
        mapTblPebHdr.put("SERIBCF", tblPebHdr.getSeriBcf());
        jackcess.insertRow(table, mapTblPebHdr);
    }

    public TblPebHdr saveAll(TblPebHdr tblPebHdr) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebHdr");
        Map<String, Object> mapTblPebHdr = new HashMap<>();
        mapTblPebHdr.put("KDKTR", tblPebHdr.getKdKtr());
        mapTblPebHdr.put("KDKTREKS", tblPebHdr.getKdKtrEks());
        mapTblPebHdr.put("JnEks", tblPebHdr.getJnEks());
        mapTblPebHdr.put("KatEks", tblPebHdr.getKatEks());
        mapTblPebHdr.put("JNDAG", tblPebHdr.getJnDag());
        mapTblPebHdr.put("JNBYR", tblPebHdr.getJnByr());
        mapTblPebHdr.put("URCARABAYAR", tblPebHdr.getUrCaraBayar());
        mapTblPebHdr.put("IDEKS", tblPebHdr.getIdEks());
        mapTblPebHdr.put("NPWPEKS", tblPebHdr.getNpwpEks());
        mapTblPebHdr.put("NamaEks", tblPebHdr.getNamaEks());
        mapTblPebHdr.put("AlmtEks", tblPebHdr.getAlmtEks());
        mapTblPebHdr.put("Niper", tblPebHdr.getNiper());
        mapTblPebHdr.put("StatusH", tblPebHdr.getStatusH());
        mapTblPebHdr.put("NoTdp", tblPebHdr.getNoTdp());
        mapTblPebHdr.put("TgTdp", tblPebHdr.getTgTdp());
        mapTblPebHdr.put("IdQq", tblPebHdr.getIdQq());
        mapTblPebHdr.put("NpwpQq", tblPebHdr.getNpwpQq());
        mapTblPebHdr.put("NamaQq", tblPebHdr.getNamaQq());
        mapTblPebHdr.put("AlmtQq", tblPebHdr.getAlmtQq());
        mapTblPebHdr.put("NAMABELI2", tblPebHdr.getNamaBeli2());
        mapTblPebHdr.put("ALMTBELI2", tblPebHdr.getAlmtBeli2());
        mapTblPebHdr.put("NEGBELI2", tblPebHdr.getNegBeli2());
        mapTblPebHdr.put("NAMABELI", tblPebHdr.getNamaBeli());
        mapTblPebHdr.put("ALMTBELI", tblPebHdr.getAlmtBeli());
        mapTblPebHdr.put("NEGBELI", tblPebHdr.getNegBeli());
        mapTblPebHdr.put("MODA", tblPebHdr.getModa());
        mapTblPebHdr.put("CARRIER", tblPebHdr.getCarrier());
        mapTblPebHdr.put("VOY", tblPebHdr.getVoy());
        mapTblPebHdr.put("BENDERA", tblPebHdr.getBendera());
        mapTblPebHdr.put("TGEKS", tblPebHdr.getTgEks());
        mapTblPebHdr.put("PELMUAT", tblPebHdr.getPelMuat());
        mapTblPebHdr.put("PELMUATEKS", tblPebHdr.getPelMuatEks());
        mapTblPebHdr.put("PELTUJUAN", tblPebHdr.getPelTujuan());
        mapTblPebHdr.put("PELBONGKAR", tblPebHdr.getPelBongkar());
        mapTblPebHdr.put("KDLOKBRG", tblPebHdr.getKdLokBrg());
        mapTblPebHdr.put("TGSIAP", tblPebHdr.getTgSiap());
        mapTblPebHdr.put("KDKTRPRIKS", tblPebHdr.getKdKtrPriks());
        mapTblPebHdr.put("GUDANGPLB", tblPebHdr.getGudangPlb());
        mapTblPebHdr.put("NEGTUJU", tblPebHdr.getNegTuju());
        mapTblPebHdr.put("DELIVERY", tblPebHdr.getDelivery());
        mapTblPebHdr.put("KDVAL", tblPebHdr.getKdVal());
        mapTblPebHdr.put("FREIGHT", tblPebHdr.getFreight());
        mapTblPebHdr.put("NILMAKLON", tblPebHdr.getNilMaklon());
        mapTblPebHdr.put("NILPAJAKLAIN", tblPebHdr.getNilPajakLain());
        mapTblPebHdr.put("KdAss", tblPebHdr.getKdAss());
        mapTblPebHdr.put("ASURANSI", tblPebHdr.getAsuransi());
        mapTblPebHdr.put("NilInv", tblPebHdr.getNilInv());
        mapTblPebHdr.put("VOLUME", tblPebHdr.getVolume());
        mapTblPebHdr.put("BRUTO", tblPebHdr.getBruto());
        mapTblPebHdr.put("NETTO", tblPebHdr.getNetto());
        mapTblPebHdr.put("KMDT", tblPebHdr.getKmdt());
        mapTblPebHdr.put("Curah", tblPebHdr.getCurah());
        mapTblPebHdr.put("CAR", tblPebHdr.getCar());
        mapTblPebHdr.put("IDPPJK", tblPebHdr.getIdPpjk());
        mapTblPebHdr.put("NPWPPPJK", tblPebHdr.getNpwpPpjk());
        mapTblPebHdr.put("NamaPpjk", tblPebHdr.getNamaPpjk());
        mapTblPebHdr.put("AlmtPpjk", tblPebHdr.getAlmtPpjk());
        mapTblPebHdr.put("FOB", tblPebHdr.getFob());
        mapTblPebHdr.put("KTRGATE", tblPebHdr.getKtrGate());
        mapTblPebHdr.put("SERIBCF", tblPebHdr.getSeriBcf());
        jackcess.insertRow(table, mapTblPebHdr);
        return tblPebHdr;
    }
}

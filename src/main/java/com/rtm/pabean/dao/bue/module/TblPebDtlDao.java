package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebDtl;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebDtlDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebDtl> getAll() throws IOException {
        List<TblPebDtl> tblPebDtlList = new ArrayList<>();
        Table pebDtl = jackcess.getTable(targetDatabase, "tblPebDtl");
        for (Row row : pebDtl) {
            TblPebDtl tblPebDtl = new TblPebDtl();
            tblPebDtl.setCar(row.getString("CAR"));
            tblPebDtl.setSeriBrg(NumberUtil.toBigInteger(row.getDouble("SERIBRG")));
            tblPebDtl.setHs(row.getString("HS"));
            tblPebDtl.setUrBrg1(row.getString("URBRG1"));
            tblPebDtl.setUrBrg2(row.getString("URBRG2"));
            tblPebDtl.setUrBrg3(row.getString("URBRG3"));
            tblPebDtl.setUrBrg4(row.getString("URBRG4"));
            tblPebDtl.setdMerk(row.getString("DMERK"));
            tblPebDtl.setSize(row.getString("SIZE"));
            tblPebDtl.setType(row.getString("TYPE"));
            tblPebDtl.setKdBrg(row.getString("KDBRG"));
            tblPebDtl.setJmKoli(NumberUtil.toBigDecimal(row.getDouble("JMKOLI")));
            tblPebDtl.setJnKoli(row.getString("JNKOLI"));
            tblPebDtl.setNetDet(NumberUtil.toBigDecimal(row.getDouble("NETDET")));
            tblPebDtl.setdVolume(NumberUtil.toBigDecimal(row.getDouble("DVolume")));
            tblPebDtl.setNegAsal(row.getString("NegAsal"));
            tblPebDtl.setDrhAsalBrg(row.getString("DRHASALBRG"));
            tblPebDtl.setdNilInv(NumberUtil.toBigDecimal(row.getDouble("DNilInv")));
            tblPebDtl.setFobPerBrg(NumberUtil.toBigDecimal(row.getDouble("FOBPERBRG")));
            tblPebDtl.setJmSatuan(NumberUtil.toBigDecimal(row.getDouble("JMSATUAN")));
            tblPebDtl.setJnSatuan(row.getString("JNSATUAN"));
            tblPebDtl.setFobPerSat(NumberUtil.toBigDecimal(row.getDouble("FOBPERSAT")));
            tblPebDtl.setKdIzin(row.getString("KDIZIN"));
            tblPebDtl.setNoIzin(row.getString("NOIZIN"));
            tblPebDtl.setTgIzin(DateUtil.toDate(row.getLocalDateTime("TGIZIN")));
            tblPebDtl.setKdPe(row.getString("KDPE"));
            tblPebDtl.setKdValPe(row.getString("KDVALPE"));
            tblPebDtl.setNilValPe(NumberUtil.toBigDecimal(row.getDouble("NILVALPE")));
            tblPebDtl.setTarifPe(NumberUtil.toBigDecimal(row.getDouble("TARIPPE")));
            tblPebDtl.setJnSatPe(row.getString("JNSATPE"));
            tblPebDtl.setJmSatPe(NumberUtil.toBigDecimal(row.getDouble("JMSATPE")));
            tblPebDtl.sethPatok(NumberUtil.toBigDecimal(row.getDouble("HPATOK")));
            tblPebDtl.setPePerBrg(NumberUtil.toBigDecimal(row.getDouble("PEPERBRG")));
            tblPebDtlList.add(tblPebDtl);
        }
        pebDtl.getDatabase().close();
        return tblPebDtlList;
    }

    public void save(List<TblPebDtl> list) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebDtl");
        list.stream().map(tblPebDtl -> {
            Map<String, Object> mapTblPebDtl = new HashMap<>();
            mapTblPebDtl.put("CAR", tblPebDtl.getCar());
            mapTblPebDtl.put("SERIBRG", tblPebDtl.getSeriBrg());
            mapTblPebDtl.put("HS", tblPebDtl.getHs());
            mapTblPebDtl.put("URBRG1", tblPebDtl.getUrBrg1());
            mapTblPebDtl.put("URBRG2", tblPebDtl.getUrBrg2());
            mapTblPebDtl.put("URBRG3", tblPebDtl.getUrBrg3());
            mapTblPebDtl.put("URBRG4", tblPebDtl.getUrBrg4());
            mapTblPebDtl.put("DMerk", tblPebDtl.getdMerk());
            mapTblPebDtl.put("SIZE", tblPebDtl.getSize());
            mapTblPebDtl.put("TYPE", tblPebDtl.getType());
            mapTblPebDtl.put("KDBRG", tblPebDtl.getKdBrg());
            mapTblPebDtl.put("JMKOLI", tblPebDtl.getJmKoli());
            mapTblPebDtl.put("JNKOLI", tblPebDtl.getJnKoli());
            mapTblPebDtl.put("NETDET", tblPebDtl.getNetDet());
            mapTblPebDtl.put("DVolume", tblPebDtl.getdVolume());
            mapTblPebDtl.put("NegAsal", tblPebDtl.getNegAsal());
            mapTblPebDtl.put("DRHASALBRG", tblPebDtl.getDrhAsalBrg());
            mapTblPebDtl.put("DNilInv", tblPebDtl.getdNilInv());
            mapTblPebDtl.put("FOBPERBRG", tblPebDtl.getFobPerBrg());
            mapTblPebDtl.put("JMSATUAN", tblPebDtl.getJmSatuan());
            mapTblPebDtl.put("JNSATUAN", tblPebDtl.getJnSatuan());
            mapTblPebDtl.put("FOBPERSAT", tblPebDtl.getFobPerSat());
            mapTblPebDtl.put("KDIZIN", tblPebDtl.getKdIzin());
            mapTblPebDtl.put("NOIZIN", tblPebDtl.getNoIzin());
            mapTblPebDtl.put("TGIZIN", tblPebDtl.getTgIzin());
            mapTblPebDtl.put("KDPE", tblPebDtl.getKdPe());
            mapTblPebDtl.put("KDVALPE", tblPebDtl.getKdValPe());
            mapTblPebDtl.put("NILVALPE", tblPebDtl.getNilValPe());
            mapTblPebDtl.put("TARIFPE", tblPebDtl.getTarifPe());
            mapTblPebDtl.put("JNSATPE", tblPebDtl.getJnSatPe());
            mapTblPebDtl.put("JMSATPE", tblPebDtl.getJmSatPe());
            mapTblPebDtl.put("HPATOK", tblPebDtl.gethPatok());
            mapTblPebDtl.put("PEPERBRG", tblPebDtl.getPePerBrg());
            return mapTblPebDtl;
        }).forEachOrdered(mapTblPebDtl -> {
            mapList.add(mapTblPebDtl);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPebDtl> saveAll(List<TblPebDtl> tblPebDtls) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebDtl");
        tblPebDtls.stream().map(tblPebDtl -> {
            Map<String, Object> mapTblPebDtl = new HashMap<>();
            mapTblPebDtl.put("CAR", tblPebDtl.getCar());
            mapTblPebDtl.put("SERIBRG", tblPebDtl.getSeriBrg());
            mapTblPebDtl.put("HS", tblPebDtl.getHs());
            mapTblPebDtl.put("URBRG1", tblPebDtl.getUrBrg1());
            mapTblPebDtl.put("URBRG2", tblPebDtl.getUrBrg2());
            mapTblPebDtl.put("URBRG3", tblPebDtl.getUrBrg3());
            mapTblPebDtl.put("URBRG4", tblPebDtl.getUrBrg4());
            mapTblPebDtl.put("DMerk", tblPebDtl.getdMerk());
            mapTblPebDtl.put("SIZE", tblPebDtl.getSize());
            mapTblPebDtl.put("TYPE", tblPebDtl.getType());
            mapTblPebDtl.put("KDBRG", tblPebDtl.getKdBrg());
            mapTblPebDtl.put("JMKOLI", tblPebDtl.getJmKoli());
            mapTblPebDtl.put("JNKOLI", tblPebDtl.getJnKoli());
            mapTblPebDtl.put("NETDET", tblPebDtl.getNetDet());
            mapTblPebDtl.put("DVolume", tblPebDtl.getdVolume());
            mapTblPebDtl.put("NegAsal", tblPebDtl.getNegAsal());
            mapTblPebDtl.put("DRHASALBRG", tblPebDtl.getDrhAsalBrg());
            mapTblPebDtl.put("DNilInv", tblPebDtl.getdNilInv());
            mapTblPebDtl.put("FOBPERBRG", tblPebDtl.getFobPerBrg());
            mapTblPebDtl.put("JMSATUAN", tblPebDtl.getJmSatuan());
            mapTblPebDtl.put("JNSATUAN", tblPebDtl.getJnSatuan());
            mapTblPebDtl.put("FOBPERSAT", tblPebDtl.getFobPerSat());
            mapTblPebDtl.put("KDIZIN", tblPebDtl.getKdIzin());
            mapTblPebDtl.put("NOIZIN", tblPebDtl.getNoIzin());
            mapTblPebDtl.put("TGIZIN", tblPebDtl.getTgIzin());
            mapTblPebDtl.put("KDPE", tblPebDtl.getKdPe());
            mapTblPebDtl.put("KDVALPE", tblPebDtl.getKdValPe());
            mapTblPebDtl.put("NILVALPE", tblPebDtl.getNilValPe());
            mapTblPebDtl.put("TARIFPE", tblPebDtl.getTarifPe());
            mapTblPebDtl.put("JNSATPE", tblPebDtl.getJnSatPe());
            mapTblPebDtl.put("JMSATPE", tblPebDtl.getJmSatPe());
            mapTblPebDtl.put("HPATOK", tblPebDtl.gethPatok());
            mapTblPebDtl.put("PEPERBRG", tblPebDtl.getPePerBrg());
            return mapTblPebDtl;
        }).forEachOrdered(mapTblPebDtl -> {
            mapList.add(mapTblPebDtl);
        });
        jackcess.insertRows(table, mapList);
        return tblPebDtls;
    }
}

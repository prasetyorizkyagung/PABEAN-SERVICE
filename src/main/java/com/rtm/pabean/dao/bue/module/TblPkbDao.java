package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPkb;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPkbDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPkb> getAll() throws IOException {
        List<TblPkb> tblPkbList = new ArrayList<>();
        Table pkb = jackcess.getTable(targetDatabase, "tblPKB");
        for (Row row : pkb) {
            TblPkb tblPkb = new TblPkb();
            tblPkb.setCar(row.getString("CAR"));
            tblPkb.setFasilitas(row.getString("Fasilitas"));
            tblPkb.setGudang(row.getString("Gudang"));
            tblPkb.setNoPhone(row.getString("NoPhone"));
            tblPkb.setNoFax(row.getString("NoFax"));
            tblPkb.setAlmtSiap(row.getString("AlmtSiap"));
            tblPkb.setJmCont20(row.getInt("JmCont20"));
            tblPkb.setJmCont40(row.getInt("JmCont40"));
            tblPkb.setPetugas(row.getString("Petugas"));
            tblPkb.setTgStuff(DateUtil.toDate(row.getLocalDateTime("TgStuff")));
            tblPkb.setAlmtStuff(row.getString("AlmtStuff"));
            tblPkb.setStuff(row.getString("Stuff"));
            tblPkb.setJnPartOf(row.getString("JnPartOf"));
            tblPkb.setKwbc(row.getString("Kwbc"));
            tblPkb.setKpker(row.getString("Kpker"));
            tblPkb.setInstFas(row.getString("InstFas"));
            tblPkb.setKdKtrFas(row.getString("KdktrFas"));
            tblPkb.setFasBc(row.getString("FasBC"));
            tblPkb.setLengkap(row.getBoolean("Lengkap"));
            tblPkb.setJnBrgGab(row.getString("JnBrgGab"));
            tblPkbList.add(tblPkb);
        }
        pkb.getDatabase().close();
        return tblPkbList;
    }

    public List<TblPkb> saveAll(List<TblPkb> tblPkbs) throws IOException {
        List<Map<String, Object>> mapsList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPKB");
        tblPkbs.stream().map(tblPkb -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPkb.getCar());
            map.put("Fasilitas", tblPkb.getFasilitas());
            map.put("Gudang", tblPkb.getGudang());
            map.put("NoPhone", tblPkb.getNoPhone());
            map.put("NoFax", tblPkb.getNoFax());
            map.put("AlmtSiap", tblPkb.getAlmtSiap());
            map.put("JmCont20", tblPkb.getJmCont20());
            map.put("JmCont40", tblPkb.getJmCont40());
            map.put("Petugas", tblPkb.getPetugas());
            map.put("TgStuff", tblPkb.getTgStuff());
            map.put("AlmtStuff", tblPkb.getAlmtStuff());
            map.put("Stuff", tblPkb.getStuff());
            map.put("JnPartOf", tblPkb.getJnPartOf());
            map.put("Kwbc", tblPkb.getKwbc());
            map.put("Kpker", tblPkb.getKpker());
            map.put("InstFas", tblPkb.getInstFas());
            map.put("KdKtrFas", tblPkb.getKdKtrFas());
            map.put("FasBC", tblPkb.getFasBc());
            map.put("Lengkap", tblPkb.getLengkap());
            map.put("JnBrgGab", tblPkb.getJnBrgGab());
            return map;
        }).forEachOrdered(map -> {
            mapsList.add(map);
        });
        jackcess.insertRows(table, mapsList);
        return tblPkbs;
    }
}

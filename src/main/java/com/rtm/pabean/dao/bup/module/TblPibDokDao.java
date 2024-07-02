package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibDok;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibDokDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibDok> getAll() throws IOException {
        List<TblPibDok> tblPibDoks = new ArrayList<>();
        Table pibDok = jackcess.getTable(targetDatabase, "tblPibDok");
        for (Row row : pibDok) {
            TblPibDok tblPibDok = new TblPibDok();
            tblPibDok.setCar(row.getString("CAR"));
            tblPibDok.setDokKd(row.getString("DokKd"));
            tblPibDok.setDokNo(row.getString("DokNo"));
            tblPibDok.setDokTg(DateUtil.toDate(row.getLocalDateTime("DokTg")));
            tblPibDok.setDokinst(row.getString("DokInst"));
            tblPibDok.setNoUrut(row.getInt("NoUrut"));
            tblPibDok.setKdGroupDok(row.getString("KdGroupDok"));
            tblPibDoks.add(tblPibDok);
        }
        pibDok.getDatabase().close();
        return tblPibDoks;
    }

    public void save(List<TblPibDok> tblPibDoks) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDok");
        tblPibDoks.stream().map(tblPibDok -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibDok.getCar());
            map.put("DokKd", tblPibDok.getDokKd());
            map.put("DokNo", tblPibDok.getDokNo());
            map.put("DokTg", tblPibDok.getDokTg());
            map.put("DokInst", tblPibDok.getDokinst());
            map.put("NoUrut", tblPibDok.getNoUrut());
            map.put("KdGroupDok", tblPibDok.getKdGroupDok());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibDok> saveAll(List<TblPibDok> tblPibDoks) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDok");
        tblPibDoks.stream().map(tblPibDok -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibDok.getCar());
            map.put("DokKd", tblPibDok.getDokKd());
            map.put("DokNo", tblPibDok.getDokNo());
            map.put("DokTg", tblPibDok.getDokTg());
            map.put("DokInst", tblPibDok.getDokinst());
            map.put("NoUrut", tblPibDok.getNoUrut());
            map.put("KdGroupDok", tblPibDok.getKdGroupDok());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibDoks;
    }
}

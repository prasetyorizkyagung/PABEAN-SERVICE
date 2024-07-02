package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebDok;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebDokDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebDok> getAll() throws IOException {
        List<TblPebDok> tblPebDokList = new ArrayList<>();
        Table pebDok = jackcess.getTable(targetDatabase, "tblPebDok");
        for (Row row : pebDok) {
            TblPebDok tblPebDok = new TblPebDok();
            tblPebDok.setCar(row.getString("CAR"));
            tblPebDok.setKdDok(row.getString("KdDok"));
            tblPebDok.setNoDok(row.getString("NoDok"));
            tblPebDok.setTgDok(DateUtil.toDate(row.getLocalDateTime("TgDok")));
            tblPebDok.setKdKtrDok(row.getString("KdKtrDok"));
            tblPebDokList.add(tblPebDok);
        }
        pebDok.getDatabase().close();
        return tblPebDokList;
    }

    public void save(List<TblPebDok> list) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebDok");
        list.stream().map(tblPebDok -> {
            Map<String, Object> mapTblPebDok = new HashMap<>();
            mapTblPebDok.put("CAR", tblPebDok.getCar());
            mapTblPebDok.put("KdDok", tblPebDok.getKdDok());
            mapTblPebDok.put("NoDok", tblPebDok.getNoDok());
            mapTblPebDok.put("TgDok", tblPebDok.getTgDok());
            mapTblPebDok.put("KdKtrDok", tblPebDok.getKdKtrDok());
            return mapTblPebDok;
        }).forEachOrdered(mapTblPebDok -> {
            mapList.add(mapTblPebDok);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPebDok> saveAll(List<TblPebDok> tblPebDoks) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebDok");
        tblPebDoks.stream().map(tblPebDok -> {
            Map<String, Object> mapTblPebDok = new HashMap<>();
            mapTblPebDok.put("CAR", tblPebDok.getCar());
            mapTblPebDok.put("KdDok", tblPebDok.getKdDok());
            mapTblPebDok.put("NoDok", tblPebDok.getNoDok());
            mapTblPebDok.put("TgDok", tblPebDok.getTgDok());
            mapTblPebDok.put("KdKtrDok", tblPebDok.getKdKtrDok());
            return mapTblPebDok;
        }).forEachOrdered(mapTblPebDok -> {
            mapList.add(mapTblPebDok);
        });
        jackcess.insertRows(table, mapList);
        return tblPebDoks;
    }
}

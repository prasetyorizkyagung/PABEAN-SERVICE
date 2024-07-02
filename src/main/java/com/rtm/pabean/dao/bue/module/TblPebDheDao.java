package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebDhe;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebDheDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebDhe> getAll() throws IOException {
        List<TblPebDhe> tblPebDheList = new ArrayList<>();
        Table pebDhe = jackcess.getTable(targetDatabase, "tblPebDhe");
        for (Row row : pebDhe) {
            TblPebDhe tblPebDhe = new TblPebDhe();
            tblPebDhe.setCar(row.getString("CAR"));
            tblPebDhe.setKdBank(row.getString("KdBank"));
            tblPebDhe.setSeriDhe(NumberUtil.toBigInteger(row.getDouble("SeriDhe")));
            tblPebDhe.setUrBank(row.getString("UrBank"));
            tblPebDheList.add(tblPebDhe);
        }
        pebDhe.getDatabase().close();
        return tblPebDheList;
    }

    public void save(List<TblPebDhe> list) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebDhe");
        List<Map<String, Object>> mapList = new ArrayList<>();
        list.stream().map(tblPebDhe -> {
            Map<String, Object> mapTblPebDhe = new HashMap<>();
            mapTblPebDhe.put("CAR", tblPebDhe.getCar());
            mapTblPebDhe.put("KdBank", tblPebDhe.getKdBank());
            mapTblPebDhe.put("SeriDhe", tblPebDhe.getSeriDhe());
            mapTblPebDhe.put("UrBank", tblPebDhe.getUrBank());
            return mapTblPebDhe;
        }).forEachOrdered(mapTblPebDhe -> {
            mapList.add(mapTblPebDhe);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPebDhe> saveAll(List<TblPebDhe> tblPebDhes) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebDhe");
        List<Map<String, Object>> mapList = new ArrayList<>();
        tblPebDhes.stream().map(tblPebDhe -> {
            Map<String, Object> mapTblPebDhe = new HashMap<>();
            mapTblPebDhe.put("CAR", tblPebDhe.getCar());
            mapTblPebDhe.put("KdBank", tblPebDhe.getKdBank());
            mapTblPebDhe.put("SeriDhe", tblPebDhe.getSeriDhe());
            mapTblPebDhe.put("UrBank", tblPebDhe.getUrBank());
            return mapTblPebDhe;
        }).forEachOrdered(mapTblPebDhe -> {
            mapList.add(mapTblPebDhe);
        });
        jackcess.insertRows(table, mapList);
        return tblPebDhes;
    }
}

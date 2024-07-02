package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebKms;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebKmsDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebKms> getAll() throws IOException {
        List<TblPebKms> tblPebKmsList = new ArrayList<>();
        Table pebKms = jackcess.getTable(targetDatabase, "tblPebKms");
        for (Row row : pebKms) {
            TblPebKms tblPebKms = new TblPebKms();
            tblPebKms.setCar(row.getString("CAR"));
            tblPebKms.setJmKemas(NumberUtil.toBigInteger(row.getInt("JmKemas")));
            tblPebKms.setJnKemas(row.getString("JnKemas"));
            tblPebKms.setMerkKemas(row.getString("MERKKEMAS"));
            tblPebKmsList.add(tblPebKms);
        }
        pebKms.getDatabase().close();
        return tblPebKmsList;
    }

    public void save(List<TblPebKms> list) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebKms");
        list.stream().map(tblPebKms -> {
            Map<String, Object> mapTblPebKms = new HashMap<>();
            mapTblPebKms.put("CAR", tblPebKms.getCar());
            mapTblPebKms.put("JmKemas", tblPebKms.getJmKemas());
            mapTblPebKms.put("JnKemas", tblPebKms.getJnKemas());
            mapTblPebKms.put("MerkKemas", tblPebKms.getMerkKemas());
            return mapTblPebKms;
        }).forEachOrdered(mapTblPebKms -> {
            mapList.add(mapTblPebKms);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPebKms> saveAll(List<TblPebKms> tblPebKmses) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebKms");
        tblPebKmses.stream().map(tblPebKms -> {
            Map<String, Object> mapTblPebKms = new HashMap<>();
            mapTblPebKms.put("CAR", tblPebKms.getCar());
            mapTblPebKms.put("JmKemas", tblPebKms.getJmKemas());
            mapTblPebKms.put("JnKemas", tblPebKms.getJnKemas());
            mapTblPebKms.put("MerkKemas", tblPebKms.getMerkKemas());
            return mapTblPebKms;
        }).forEachOrdered(mapTblPebKms -> {
            mapList.add(mapTblPebKms);
        });
        jackcess.insertRows(table, mapList);
        return tblPebKmses;
    }
}

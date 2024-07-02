package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebCon;
import com.rtm.pabean.utils.JackcessUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebConDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebCon> getAll() throws IOException {
        List<TblPebCon> tblPebCons = new ArrayList<>();
        Table pebCon = jackcess.getTable(targetDatabase, "tblPebCon");
        for (Row row : pebCon) {
            TblPebCon tblPebCon = new TblPebCon();
            tblPebCon.setCar(row.getString("CAR"));
            tblPebCon.setNoCont(row.getString("NoCont"));
            tblPebCon.setSize(row.getString("Size"));
            tblPebCon.setType(row.getString("Type"));
            tblPebCon.setNoSegel(row.getString("NoSegel"));
            tblPebCon.setStuff(row.getString("Stuff"));
            tblPebCon.setJnPartOf(row.getString("JnPartOf"));
            tblPebCon.setContKodeTipe(row.getString("ContKodeTipe"));
            tblPebCons.add(tblPebCon);
        }
        pebCon.getDatabase().close();
        return tblPebCons;
    }

    public void save(List<TblPebCon> list) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebCon");
        List<Map<String, Object>> mapList = new ArrayList<>();
        list.stream().map(tblPebCon -> {
            Map<String, Object> mapTblPebCon = new HashMap<>();
            mapTblPebCon.put("CAR", tblPebCon.getCar());
            mapTblPebCon.put("NoCont", tblPebCon.getNoCont());
            mapTblPebCon.put("Size", tblPebCon.getSize());
            mapTblPebCon.put("Type", tblPebCon.getType());
            mapTblPebCon.put("NoSegel", tblPebCon.getNoSegel());
            mapTblPebCon.put("Stuff", tblPebCon.getStuff());
            mapTblPebCon.put("JnPartOf", tblPebCon.getJnPartOf());
            // mapTblPebCon.put("ContKodeTipe", tblPebCon.getContKodeTipe());
            return mapTblPebCon;
        }).forEachOrdered(mapTblPebCon -> {
            mapList.add(mapTblPebCon);
        });
        jackcess.insertRows(table, mapList);
    }
    
    public List<TblPebCon> saveAll(List<TblPebCon> tblPebCons) throws IOException {
        Table table = jackcess.getTable(targetDatabase, "tblPebCon");
        List<Map<String, Object>> mapList = new ArrayList<>();
        tblPebCons.stream().map(tblPebCon -> {
            Map<String, Object> mapTblPebCon = new HashMap<>();
            mapTblPebCon.put("CAR", tblPebCon.getCar());
            mapTblPebCon.put("NoCont", tblPebCon.getNoCont());
            mapTblPebCon.put("Size", tblPebCon.getSize());
            mapTblPebCon.put("Type", tblPebCon.getType());
            mapTblPebCon.put("NoSegel", tblPebCon.getNoSegel());
            mapTblPebCon.put("Stuff", tblPebCon.getStuff());
            mapTblPebCon.put("JnPartOf", tblPebCon.getJnPartOf());
            // mapTblPebCon.put("ContKodeTipe", tblPebCon.getContKodeTipe());
            return mapTblPebCon;
        }).forEachOrdered(mapTblPebCon -> {
            mapList.add(mapTblPebCon);
        });
        jackcess.insertRows(table, mapList);
        return tblPebCons;
    }
}

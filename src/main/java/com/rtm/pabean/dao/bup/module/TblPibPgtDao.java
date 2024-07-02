package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibPgt;
import com.rtm.pabean.utils.JackcessUtil;

@Repository
public class TblPibPgtDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibPgt> getAll() throws IOException {
        List<TblPibPgt> tblPibPgts = new ArrayList<>();
        Table pibPgt = jackcess.getTable(targetDatabase, "tblPibPgt");
        for (Row row : pibPgt) {
            TblPibPgt tblPibPgt = new TblPibPgt();
            tblPibPgt.setCar(row.getString("CAR"));
            tblPibPgt.setKdBeban(row.getString("KdBeban"));
            tblPibPgt.setKdFasil(row.getString("KdFasil"));
            tblPibPgt.setNilBeban(Double.valueOf(row.getString("NilBeban")));
            tblPibPgt.setNpwp(row.getString("Npwp"));
            tblPibPgts.add(tblPibPgt);
        }
        pibPgt.getDatabase().close();
        return tblPibPgts;
    }

    public void save(List<TblPibPgt> tblPibPgts) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibPgt");
        tblPibPgts.stream().map(tblPibPgt -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibPgt.getCar());
            map.put("KdBeban", tblPibPgt.getKdBeban());
            map.put("KdFasil", tblPibPgt.getKdFasil());
            map.put("NilBeban", tblPibPgt.getNilBeban());
            map.put("Npwp", tblPibPgt.getNpwp());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibPgt> saveAll(List<TblPibPgt> tblPibPgts) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibPgt");
        tblPibPgts.stream().map(tblPibPgt -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibPgt.getCar());
            map.put("KdBeban", tblPibPgt.getKdBeban());
            map.put("KdFasil", tblPibPgt.getKdFasil());
            map.put("NilBeban", tblPibPgt.getNilBeban());
            map.put("Npwp", tblPibPgt.getNpwp());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibPgts;
    }
}

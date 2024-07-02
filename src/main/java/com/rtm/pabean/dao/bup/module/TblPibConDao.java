package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibCon;
import com.rtm.pabean.utils.JackcessUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibConDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibCon> getAll() throws IOException {
        List<TblPibCon> tblPibCons = new ArrayList<>();
        Table pibCon = jackcess.getTable(targetDatabase, "tblPibCon");
        for (Row row : pibCon) {
            TblPibCon tblPibCon = new TblPibCon();
            tblPibCon.setCar(row.getString("CAR"));
            tblPibCon.setContNo(row.getString("ContNo"));
            tblPibCon.setContTipe(row.getString("ContTipe"));
            tblPibCon.setContUkur(row.getString("ContUkur"));
            tblPibCon.setContKodeTipe(row.getString("ContKodeTipe"));
            tblPibCons.add(tblPibCon);
        }
        pibCon.getDatabase().close();
        return tblPibCons;
    }

    public void save(List<TblPibCon> tblPibCons) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibCon");
        tblPibCons.stream().map(tblPibCon -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibCon.getCar());
            map.put("ContNo", tblPibCon.getContNo());
            map.put("ContUkur", tblPibCon.getContUkur());
            map.put("ContTipe", tblPibCon.getContTipe());
            map.put("ContKodeTipe", tblPibCon.getContKodeTipe());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibCon> saveAll(List<TblPibCon> tblPibCons) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibCon");
        tblPibCons.stream().map(tblPibCon -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibCon.getCar());
            map.put("ContNo", tblPibCon.getContNo());
            map.put("ContUkur", tblPibCon.getContUkur());
            map.put("ContTipe", tblPibCon.getContTipe());
            map.put("ContKodeTipe", tblPibCon.getContKodeTipe());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibCons;
    }
}

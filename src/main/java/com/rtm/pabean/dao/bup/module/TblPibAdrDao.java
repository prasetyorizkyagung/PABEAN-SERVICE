package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibAdr;
import com.rtm.pabean.utils.JackcessUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibAdrDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibAdr> getAll() throws IOException {
        List<TblPibAdr> tblPibAdrs = new ArrayList<>();
        Table pibAdr = jackcess.getTable(targetDatabase, "tblPibAddress");
        if (pibAdr == null) {
            return tblPibAdrs;
        }
        for (Row row : pibAdr) {
            TblPibAdr tblPibAdr = new TblPibAdr();
            tblPibAdr.setCar(row.getString("CAR"));
            tblPibAdr.setEntityCode(row.getInt("EntityCode"));
            tblPibAdr.setName(row.getString("Name"));
            tblPibAdr.setAddress(row.getString("Address"));
            tblPibAdrs.add(tblPibAdr);
        }
        pibAdr.getDatabase().close();
        return tblPibAdrs;
    }

    public void save(List<TblPibAdr> tblPibAdrs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibAddress");
        tblPibAdrs.stream().map(tblPibAdr -> {
            Map<String, Object> mapTblPibAdr = new HashMap<>();
            mapTblPibAdr.put("CAR", tblPibAdr.getCar());
            mapTblPibAdr.put("EntityCode", tblPibAdr.getEntityCode());
            mapTblPibAdr.put("Name", tblPibAdr.getName());
            mapTblPibAdr.put("Address", tblPibAdr.getAddress());
            return mapTblPibAdr;
        }).forEachOrdered(mapTblPibAdr -> {
            mapList.add(mapTblPibAdr);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibAdr> saveAll(List<TblPibAdr> tblPibAdrs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibAddress");
        tblPibAdrs.stream().map(tblPibAdr -> {
            Map<String, Object> mapTblPibAdr = new HashMap<>();
            mapTblPibAdr.put("CAR", tblPibAdr.getCar());
            mapTblPibAdr.put("EntityCode", tblPibAdr.getEntityCode());
            mapTblPibAdr.put("Name", tblPibAdr.getName());
            mapTblPibAdr.put("Address", tblPibAdr.getAddress());
            return mapTblPibAdr;
        }).forEachOrdered(mapTblPibAdr -> {
            mapList.add(mapTblPibAdr);
        });
        jackcess.insertRows(table, mapList);
        return tblPibAdrs;
    }
}
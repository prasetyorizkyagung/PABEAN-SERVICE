package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibKms;
import com.rtm.pabean.utils.JackcessUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibKmsDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibKms> getAll() throws IOException {
        List<TblPibKms> tblPibKmsList = new ArrayList<>();
        Table pibKms = jackcess.getTable(targetDatabase, "tblPibKms");
        for (Row row : pibKms) {
            TblPibKms tblPibKms = new TblPibKms();
            tblPibKms.setCar(row.getString("CAR"));
            tblPibKms.setJmKemas(row.getInt("JmKemas"));
            tblPibKms.setJnKemas(row.getString("JnKemas"));
            tblPibKms.setMerkKemas(row.getString("merkkemas"));
            tblPibKmsList.add(tblPibKms);
        }
        pibKms.getDatabase().close();
        return tblPibKmsList;
    }

    public void save(List<TblPibKms> tblPibKmses) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibKms");
        tblPibKmses.stream().map(tblPibKms -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibKms.getCar());
            map.put("JmKemas", tblPibKms.getJmKemas());
            map.put("JnKemas", tblPibKms.getJnKemas());
            map.put("merkkemas", tblPibKms.getMerkKemas());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }
    
    public List<TblPibKms> saveAll(List<TblPibKms> tblPibKmses) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibKms");
        tblPibKmses.stream().map(tblPibKms -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibKms.getCar());
            map.put("JmKemas", tblPibKms.getJmKemas());
            map.put("JnKemas", tblPibKms.getJnKemas());
            map.put("merkkemas", tblPibKms.getMerkKemas());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibKmses;
    }
}

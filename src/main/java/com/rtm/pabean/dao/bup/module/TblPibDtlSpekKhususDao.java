package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibDtlSpekKhusus;
import com.rtm.pabean.utils.JackcessUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibDtlSpekKhususDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibDtlSpekKhusus> getAll() throws IOException {
        List<TblPibDtlSpekKhusus> tblPibDtlSpekKhusus = new ArrayList<>();
        Table pibDtlSpekKhusus = jackcess.getTable(targetDatabase, "tblPibDtlSpekKhusus");
        for (Row row : pibDtlSpekKhusus) {
            TblPibDtlSpekKhusus spekKhusus = new TblPibDtlSpekKhusus();
            spekKhusus.setCar(row.getString("Car"));
            spekKhusus.setSerial(row.getInt("Serial"));
            spekKhusus.setCas1(row.getString("CAS1"));
            spekKhusus.setCas2(row.getString("CAS2"));
            tblPibDtlSpekKhusus.add(spekKhusus);
        }
        pibDtlSpekKhusus.getDatabase().close();
        return tblPibDtlSpekKhusus;
    }

    public void save(List<TblPibDtlSpekKhusus> tblPibDtlSpekKhususes) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlSpekKhusus");
        tblPibDtlSpekKhususes.stream().map(tblPibDtlSpekKhusus -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlSpekKhusus.getCar());
            map.put("Serial", tblPibDtlSpekKhusus.getSerial());
            map.put("CAS1", tblPibDtlSpekKhusus.getCas1());
            map.put("CAS2", tblPibDtlSpekKhusus.getCas2());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibDtlSpekKhusus> saveAll(List<TblPibDtlSpekKhusus> tblPibDtlSpekKhususes) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlSpekKhusus");
        tblPibDtlSpekKhususes.stream().map(tblPibDtlSpekKhusus -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlSpekKhusus.getCar());
            map.put("Serial", tblPibDtlSpekKhusus.getSerial());
            map.put("CAS1", tblPibDtlSpekKhusus.getCas1());
            map.put("CAS2", tblPibDtlSpekKhusus.getCas2());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibDtlSpekKhususes;
    }
}

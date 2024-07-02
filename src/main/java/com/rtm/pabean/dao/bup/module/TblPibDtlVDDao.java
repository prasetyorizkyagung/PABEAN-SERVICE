package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibDtlVD;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibDtlVDDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibDtlVD> getAll() throws IOException {
        List<TblPibDtlVD> tblPibDtlVDs = new ArrayList<>();
        Table pibDtlVd = jackcess.getTable(targetDatabase, "tblPibDtlVD");
        for (Row row : pibDtlVd) {
            TblPibDtlVD tblPibDtlVD = new TblPibDtlVD();
            tblPibDtlVD.setCar(row.getString("Car"));
            tblPibDtlVD.setSerial(NumberUtil.toBigInteger(row.getInt("Serial")));
            tblPibDtlVD.setJenis(row.getString("Jenis"));
            tblPibDtlVD.setNilai(NumberUtil.toBigDecimal(row.getDouble("Nilai")));
            tblPibDtlVD.setTgJatuhTempo(DateUtil.toDate(row.getLocalDateTime("TgJatuhTempo")));
            tblPibDtlVDs.add(tblPibDtlVD);
        }
        pibDtlVd.getDatabase().close();
        return tblPibDtlVDs;
    }

    public void save(List<TblPibDtlVD> tblPibDtlVDs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlVD");
        tblPibDtlVDs.stream().map(tblPibDtlVD -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlVD.getCar());
            map.put("Serial", tblPibDtlVD.getSerial());
            map.put("Jenis", tblPibDtlVD.getJenis());
            map.put("Nilai", tblPibDtlVD.getNilai());
            map.put("TgJatuhTempo", tblPibDtlVD.getTgJatuhTempo());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibDtlVD> saveAll(List<TblPibDtlVD> tblPibDtlVDs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlVD");
        tblPibDtlVDs.stream().map(tblPibDtlVD -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlVD.getCar());
            map.put("Serial", tblPibDtlVD.getSerial());
            map.put("Jenis", tblPibDtlVD.getJenis());
            map.put("Nilai", tblPibDtlVD.getNilai());
            map.put("TgJatuhTempo", tblPibDtlVD.getTgJatuhTempo());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibDtlVDs;
    }
}

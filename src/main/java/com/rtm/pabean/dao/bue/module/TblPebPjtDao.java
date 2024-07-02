package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.rtm.pabean.model.bue.module.TblPebPjt;
import com.rtm.pabean.utils.JackcessUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblPebPjtDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPebPjt> getAll() throws IOException {
        List<TblPebPjt> tblPebPjts = new ArrayList<>();
        Table pebPjt = jackcess.getTable(targetDatabase, "tblPebPjt");
        for (Row row : pebPjt) {
            TblPebPjt tblPebPjt = new TblPebPjt();
            tblPebPjt.setCar(row.getString("CAR"));
            tblPebPjt.setSeriBrg(row.getInt("SeriBrg"));
            tblPebPjt.setIdeksT(row.getString("IdeksT"));
            tblPebPjt.setNpwpEksT(row.getString("NpwpEksT"));
            tblPebPjt.setNamaEksT(row.getString("NamaEksT"));
            tblPebPjt.setAlmtEksT(row.getString("AlmtEksT"));
            tblPebPjt.setNamaBeliT(row.getString("NamaBeliT"));
            tblPebPjt.setAlmtBeliT(row.getString("AlmtBeliT"));
            tblPebPjt.setNegBeliT(row.getString("NegBeliT"));
            tblPebPjts.add(tblPebPjt);
        }
        pebPjt.getDatabase().close();
        return tblPebPjts;
    }

    public void save(List<TblPebPjt> list) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebPjt");
        list.stream().map(tblPebPjt -> {
            Map<String, Object> mapTblPebPjt = new HashMap<>();
            mapTblPebPjt.put("CAR", tblPebPjt.getCar());
            mapTblPebPjt.put("SeriBrg", tblPebPjt.getSeriBrg());
            mapTblPebPjt.put("IdEksT", tblPebPjt.getIdeksT());
            mapTblPebPjt.put("NpwpEksT", tblPebPjt.getNpwpEksT());
            mapTblPebPjt.put("NamaEksT", tblPebPjt.getNamaEksT());
            mapTblPebPjt.put("AlmtEksT", tblPebPjt.getAlmtEksT());
            mapTblPebPjt.put("NamaBeliT", tblPebPjt.getNamaBeliT());
            mapTblPebPjt.put("AlmtBeliT", tblPebPjt.getAlmtBeliT());
            mapTblPebPjt.put("NegBeliT", tblPebPjt.getNegBeliT());
            return mapTblPebPjt;
        }).forEachOrdered(mapTblPebPjt -> {
            mapList.add(mapTblPebPjt);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPebPjt> saveAll(List<TblPebPjt> tblPebPjts) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPebPjt");
        tblPebPjts.stream().map(tblPebPjt -> {
            Map<String, Object> mapTblPebPjt = new HashMap<>();
            mapTblPebPjt.put("CAR", tblPebPjt.getCar());
            mapTblPebPjt.put("SeriBrg", tblPebPjt.getSeriBrg());
            mapTblPebPjt.put("IdEksT", tblPebPjt.getIdeksT());
            mapTblPebPjt.put("NpwpEksT", tblPebPjt.getNpwpEksT());
            mapTblPebPjt.put("NamaEksT", tblPebPjt.getNamaEksT());
            mapTblPebPjt.put("AlmtEksT", tblPebPjt.getAlmtEksT());
            mapTblPebPjt.put("NamaBeliT", tblPebPjt.getNamaBeliT());
            mapTblPebPjt.put("AlmtBeliT", tblPebPjt.getAlmtBeliT());
            mapTblPebPjt.put("NegBeliT", tblPebPjt.getNegBeliT());
            return mapTblPebPjt;
        }).forEachOrdered(mapTblPebPjt -> {
            mapList.add(mapTblPebPjt);
        });
        jackcess.insertRows(table, mapList);
        return tblPebPjts;
    }
}

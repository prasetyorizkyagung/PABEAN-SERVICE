package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibDtlDok;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibDtlDokDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibDtlDok> getAll() throws IOException {
        List<TblPibDtlDok> tblPibDtlDoks = new ArrayList<>();
        Table pibDtlDok = jackcess.getTable(targetDatabase, "tblPibDtlDok");
        for (Row row : pibDtlDok) {
            TblPibDtlDok tblPibDtlDok = new TblPibDtlDok();
            tblPibDtlDok.setCar(row.getString("Car"));
            tblPibDtlDok.setSerial(NumberUtil.toBigInteger(row.getInt("Serial")));
            tblPibDtlDok.setKdFasDtl(row.getString("KdFasDtl"));
            tblPibDtlDok.setNoUrut(NumberUtil.toBigInteger(row.getInt("NoUrut")));
            tblPibDtlDok.setDokKd(row.getString("DokKd"));
            tblPibDtlDok.setDokNo(row.getString("DokNo"));
            tblPibDtlDok.setDokTg(DateUtil.toDate(row.getLocalDateTime("DokTg")));
            tblPibDtlDok.setKdGroupDok(row.getString("KdGroupDok"));
            tblPibDtlDok.setNoSeriBrgSkep(row.getString("NoSeriBrgSkep"));
            tblPibDtlDoks.add(tblPibDtlDok);
        }
        pibDtlDok.getDatabase().close();
        return tblPibDtlDoks;
    }

    public void save(List<TblPibDtlDok> tblPibDtlDoks) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlDok");
        tblPibDtlDoks.stream().map(tblPibDtlDok -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlDok.getCar());
            map.put("Serial", tblPibDtlDok.getSerial());
            map.put("KdFasDtl", tblPibDtlDok.getKdFasDtl());
            map.put("NoUrut", tblPibDtlDok.getNoUrut());
            map.put("DokKd", tblPibDtlDok.getDokKd());
            map.put("DokNo", tblPibDtlDok.getDokNo());
            map.put("DokTg", tblPibDtlDok.getDokTg());
            map.put("KdGroupDok", tblPibDtlDok.getKdGroupDok());
            map.put("NoSeriBrgSkep", tblPibDtlDok.getNoSeriBrgSkep());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibDtlDok> saveAll(List<TblPibDtlDok> tblPibDtlDoks) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtlDok");
        tblPibDtlDoks.stream().map(tblPibDtlDok -> {
            Map<String, Object> map = new HashMap<>();
            map.put("Car", tblPibDtlDok.getCar());
            map.put("Serial", tblPibDtlDok.getSerial());
            map.put("KdFasDtl", tblPibDtlDok.getKdFasDtl());
            map.put("NoUrut", tblPibDtlDok.getNoUrut());
            map.put("DokKd", tblPibDtlDok.getDokKd());
            map.put("DokNo", tblPibDtlDok.getDokNo());
            map.put("DokTg", tblPibDtlDok.getDokTg());
            map.put("KdGroupDok", tblPibDtlDok.getKdGroupDok());
            map.put("NoSeriBrgSkep", tblPibDtlDok.getNoSeriBrgSkep());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibDtlDoks;
    }
}

package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibDtl;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibDtlDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibDtl> getAll() throws IOException {
        List<TblPibDtl> tblPibDtls = new ArrayList<>();
        Table pibDtl = jackcess.getTable(targetDatabase, "tblPibDtl");
        for (Row row : pibDtl) {
            TblPibDtl tblPibDtl = new TblPibDtl();
            tblPibDtl.setCar(row.getString("CAR"));
            tblPibDtl.setSerial(NumberUtil.toBigInteger(row.getShort("Serial")));
            tblPibDtl.setNoHs(row.getString("NoHs"));
            tblPibDtl.setSeriTrp(NumberUtil.toBigInteger(row.getShort("SeriTrp")));
            tblPibDtl.setBrgUrai(row.getString("BrgUrai"));
            tblPibDtl.setMerk(row.getString("Merk"));
            tblPibDtl.setTipe(row.getString("Tipe"));
            tblPibDtl.setSpfLain(row.getString("SpfLain"));
            tblPibDtl.setBrgAsal(row.getString("BrgAsal"));
            tblPibDtl.setDNilInv(NumberUtil.toBigDecimal(row.getDouble("DNilInv")));
            tblPibDtl.setDCif(NumberUtil.toBigDecimal(row.getDouble("DCif")));
            tblPibDtl.setKdSat(row.getString("KdSat"));
            tblPibDtl.setJmlSat(NumberUtil.toBigDecimal(row.getDouble("JmlSat")));
            tblPibDtl.setKemasJn(row.getString("KemasJn"));
            tblPibDtl.setKemasJm(NumberUtil.toBigInteger(row.getInt("KemasJm")));
            tblPibDtl.setSatBmJm(NumberUtil.toBigDecimal(row.getDouble("SatBmJm")));
            tblPibDtl.setSatCukJm(NumberUtil.toBigDecimal(row.getDouble("SatCukJm")));
            tblPibDtl.setNettoDtl(NumberUtil.toBigDecimal(row.getDouble("NettoDtl")));
            tblPibDtl.setKdFasDtl(row.getString("KdFasDtl"));
            // tblPibDtl.setDtlOk(row.getBoolean("DtlOk"));
            tblPibDtl.setFlBarangBaru(row.getString("FlBarangBaru"));
            tblPibDtl.setFlLartas(row.getString("FlLartas"));
            tblPibDtl.setKatLartas(row.getString("KatLartas"));
            tblPibDtl.setSpekTarif(row.getString("SpekTarif"));
            tblPibDtl.setDNilCuk(NumberUtil.toBigDecimal(row.getDouble("DNilCuk")));
            tblPibDtl.setJmPC(NumberUtil.toBigDecimal(row.getDouble("JmPc")));
            tblPibDtl.setSaldoAwalPC(NumberUtil.toBigDecimal(row.getDouble("SaldoAwalPC")));
            tblPibDtl.setSaldoAkhirPC(NumberUtil.toBigDecimal(row.getDouble("SaldoAkhirPC")));
            tblPibDtls.add(tblPibDtl);
        }
        pibDtl.getDatabase().close();
        tblPibDtls.sort(Comparator.comparing(TblPibDtl::getSerial));
        return tblPibDtls;
    }

    public void save(List<TblPibDtl> tblPibDtls) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtl");
        tblPibDtls.stream().map(tblPibDtl -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibDtl.getCar());
            map.put("Serial", tblPibDtl.getSerial());
            map.put("NoHs", tblPibDtl.getNoHs());
            map.put("SeriTrp", tblPibDtl.getSeriTrp());
            map.put("BrgUrai", tblPibDtl.getBrgUrai());
            map.put("Merk", tblPibDtl.getMerk());
            map.put("Tipe", tblPibDtl.getTipe());
            map.put("SpfLain", tblPibDtl.getSpfLain());
            map.put("BrgAsal", tblPibDtl.getBrgAsal());
            map.put("DNilInv", tblPibDtl.getDNilInv());
            map.put("DCif", tblPibDtl.getDCif());
            map.put("KdSat", tblPibDtl.getKdSat());
            map.put("JmlSat", tblPibDtl.getJmlSat());
            map.put("KemasJn", tblPibDtl.getKemasJn());
            map.put("KemasJm", tblPibDtl.getKemasJm());
            map.put("SatBmJm", tblPibDtl.getSatBmJm());
            map.put("SatCukJm", tblPibDtl.getSatCukJm());
            map.put("NettoDtl", tblPibDtl.getNettoDtl());
            map.put("KdFasDtl", tblPibDtl.getKdFasDtl());
            map.put("DtlOk", tblPibDtl.isDtlOk());
            map.put("FlBarangBaru", tblPibDtl.getFlBarangBaru());
            map.put("FlLartas", tblPibDtl.getFlLartas());
            map.put("KatLartas", tblPibDtl.getKatLartas());
            map.put("SpekTarif", tblPibDtl.getSpekTarif());
            map.put("DNilCuk", tblPibDtl.getDNilCuk());
            map.put("JmPC", tblPibDtl.getJmPC());
            map.put("SaldoAwalPC", tblPibDtl.getSaldoAwalPC());
            map.put("SaldoAkhirPC", tblPibDtl.getSaldoAkhirPC());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibDtl> saveAll(List<TblPibDtl> tblPibDtls) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibDtl");
        tblPibDtls.stream().map(tblPibDtl -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibDtl.getCar());
            map.put("Serial", tblPibDtl.getSerial());
            map.put("NoHs", tblPibDtl.getNoHs());
            map.put("SeriTrp", tblPibDtl.getSeriTrp());
            map.put("BrgUrai", tblPibDtl.getBrgUrai());
            map.put("Merk", tblPibDtl.getMerk());
            map.put("Tipe", tblPibDtl.getTipe());
            map.put("SpfLain", tblPibDtl.getSpfLain());
            map.put("BrgAsal", tblPibDtl.getBrgAsal());
            map.put("DNilInv", tblPibDtl.getDNilInv());
            map.put("DCif", tblPibDtl.getDCif());
            map.put("KdSat", tblPibDtl.getKdSat());
            map.put("JmlSat", tblPibDtl.getJmlSat());
            map.put("KemasJn", tblPibDtl.getKemasJn());
            map.put("KemasJm", tblPibDtl.getKemasJm());
            map.put("SatBmJm", tblPibDtl.getSatBmJm());
            map.put("SatCukJm", tblPibDtl.getSatCukJm());
            map.put("NettoDtl", tblPibDtl.getNettoDtl());
            map.put("KdFasDtl", tblPibDtl.getKdFasDtl());
            map.put("DtlOk", tblPibDtl.isDtlOk());
            map.put("FlBarangBaru", tblPibDtl.getFlBarangBaru());
            map.put("FlLartas", tblPibDtl.getFlLartas());
            map.put("KatLartas", tblPibDtl.getKatLartas());
            map.put("SpekTarif", tblPibDtl.getSpekTarif());
            map.put("DNilCuk", tblPibDtl.getDNilCuk());
            map.put("JmPC", tblPibDtl.getJmPC());
            map.put("SaldoAwalPC", tblPibDtl.getSaldoAwalPC());
            map.put("SaldoAkhirPC", tblPibDtl.getSaldoAkhirPC());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibDtls;
    }
}

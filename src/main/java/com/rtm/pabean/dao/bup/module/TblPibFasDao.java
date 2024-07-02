package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibFas;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibFasDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibFas> getAll() throws IOException {
        List<TblPibFas> tblPibFasList = new ArrayList<>();
        Table pibFas = jackcess.getTable(targetDatabase, "tblPibFas");
        for (Row row : pibFas) {
            TblPibFas tblPibFas = new TblPibFas();
            tblPibFas.setCar(row.getString("CAR"));
            tblPibFas.setSerial(NumberUtil.toBigInteger(row.getShort("Serial")));
            tblPibFas.setKdFasBM(row.getString("KdFasBM"));
            tblPibFas.setFasBM(NumberUtil.toBigDecimal(row.getDouble("FasBM")));
            tblPibFas.setKdFasCuk(row.getString("KdFasCuk"));
            tblPibFas.setFasCuk(NumberUtil.toBigDecimal(row.getDouble("FasCuk")));
            tblPibFas.setKdFasPPN(row.getString("KdFasPpn"));
            tblPibFas.setFasPPN(NumberUtil.toBigDecimal(row.getDouble("FasPpn")));
            tblPibFas.setKdFasPPH(row.getString("KdFasPph"));
            tblPibFas.setFasPPH(NumberUtil.toBigDecimal(row.getDouble("FasPph")));
            tblPibFas.setKdFasPBM(row.getString("KdFasPbm"));
            tblPibFas.setFasPBM(NumberUtil.toBigDecimal(row.getDouble("FasPbm")));
            tblPibFas.setKdFasBMAD(row.getString("KdFasBMAD"));
            tblPibFas.setFasBMAD(NumberUtil.toBigDecimal(row.getDouble("FasBMAD")));
            tblPibFas.setBMADS(row.getString("BMADS"));
            tblPibFas.setKdFasBMTP(row.getString("KdFasBMTP"));
            tblPibFas.setFasBMTP(NumberUtil.toBigDecimal(row.getDouble("FasBMTP")));
            tblPibFas.setBMTPS(row.getString("BMTPS"));
            tblPibFas.setKdFasBMIM(row.getString("KdFasBMIM"));
            tblPibFas.setFasBMIM(NumberUtil.toBigDecimal(row.getDouble("FasBMIM")));
            tblPibFas.setBMIMS(row.getString("BMIMS"));
            tblPibFas.setKdFasBMPB(row.getString("KdFasBMPB"));
            tblPibFas.setFasBMPB(NumberUtil.toBigDecimal(row.getDouble("FasBMPB")));
            tblPibFas.setBMPBS(row.getString("BMPBS"));
            tblPibFasList.add(tblPibFas);
        }
        pibFas.getDatabase().close();
        return tblPibFasList;
    }

    public void save(List<TblPibFas> tblPibFases) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibFas");
        tblPibFases.stream().map(tblPibFas -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibFas.getCar());
            map.put("Serial", tblPibFas.getSerial());
            map.put("KdFasBM", tblPibFas.getKdFasBM());
            map.put("FasBM", tblPibFas.getFasBM());
            map.put("KdFasCuk", tblPibFas.getKdFasCuk());
            map.put("FasCuk", tblPibFas.getFasCuk());
            map.put("KdFasPpn", tblPibFas.getKdFasPPN());
            map.put("FasPpn", tblPibFas.getFasPPN());
            map.put("KdFasPph", tblPibFas.getKdFasPPH());
            map.put("FasPph", tblPibFas.getFasPPH());
            map.put("KdFasPbm", tblPibFas.getKdFasPBM());
            map.put("FasPbm", tblPibFas.getFasPBM());
            map.put("KdFasBMAD", tblPibFas.getKdFasBMAD());
            map.put("FasBMAD", tblPibFas.getFasBMAD());
            map.put("BMADS", tblPibFas.getBMADS());
            map.put("KdFasBMTP", tblPibFas.getKdFasBMTP());
            map.put("FasBMTP", tblPibFas.getFasBMTP());
            map.put("BMTPS", tblPibFas.getBMTPS());
            map.put("KdFasBMIM", tblPibFas.getKdFasBMIM());
            map.put("FasBMIM", tblPibFas.getFasBMIM());
            map.put("BMIMS", tblPibFas.getBMIMS());
            map.put("KdFasBMPB", tblPibFas.getKdFasBMPB());
            map.put("FasBMPB", tblPibFas.getFasBMPB());
            map.put("BMPBS", tblPibFas.getBMPBS());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibFas> saveAll(List<TblPibFas> tblPibFases) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibFas");
        tblPibFases.stream().map(tblPibFas -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibFas.getCar());
            map.put("Serial", tblPibFas.getSerial());
            map.put("KdFasBM", tblPibFas.getKdFasBM());
            map.put("FasBM", tblPibFas.getFasBM());
            map.put("KdFasCuk", tblPibFas.getKdFasCuk());
            map.put("FasCuk", tblPibFas.getFasCuk());
            map.put("KdFasPpn", tblPibFas.getKdFasPPN());
            map.put("FasPpn", tblPibFas.getFasPPN());
            map.put("KdFasPph", tblPibFas.getKdFasPPH());
            map.put("FasPph", tblPibFas.getFasPPH());
            map.put("KdFasPbm", tblPibFas.getKdFasPBM());
            map.put("FasPbm", tblPibFas.getFasPBM());
            map.put("KdFasBMAD", tblPibFas.getKdFasBMAD());
            map.put("FasBMAD", tblPibFas.getFasBMAD());
            map.put("BMADS", tblPibFas.getBMADS());
            map.put("KdFasBMTP", tblPibFas.getKdFasBMTP());
            map.put("FasBMTP", tblPibFas.getFasBMTP());
            map.put("BMTPS", tblPibFas.getBMTPS());
            map.put("KdFasBMIM", tblPibFas.getKdFasBMIM());
            map.put("FasBMIM", tblPibFas.getFasBMIM());
            map.put("BMIMS", tblPibFas.getBMIMS());
            map.put("KdFasBMPB", tblPibFas.getKdFasBMPB());
            map.put("FasBMPB", tblPibFas.getFasBMPB());
            map.put("BMPBS", tblPibFas.getBMPBS());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibFases;
    }
}

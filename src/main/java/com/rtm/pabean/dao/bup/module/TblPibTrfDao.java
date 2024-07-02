package com.rtm.pabean.dao.bup.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;
import com.rtm.pabean.model.bup.module.TblPibTrf;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibTrfDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibTrf> getAll() throws IOException {
        List<TblPibTrf> tblPibTrfs = new ArrayList<>();
        Table pibTrf = jackcess.getTable(targetDatabase, "tblPibTrf");
        for (Row row : pibTrf) {
            TblPibTrf tblPibTrf = new TblPibTrf();
            tblPibTrf.setCar(row.getString("CAR"));
            tblPibTrf.setNoHs(row.getString("NoHs"));
            tblPibTrf.setSeriTrp(row.getShort("SeriTrp"));
            tblPibTrf.setKdTrpBM(row.getString("KdTrpBm"));
            tblPibTrf.setKdSatBM(row.getString("KdSatBm"));
            tblPibTrf.setTrpBM(NumberUtil.fixDouble(row.getDouble("TrpBm")));
            tblPibTrf.setKdCuk(row.getString("KdCuk"));
            tblPibTrf.setKdTrpCuk(row.getString("KdTrpCuk"));
            tblPibTrf.setKdSatCuk(row.getString("KdSatCuk"));
            tblPibTrf.setTrpCuk(NumberUtil.fixDouble(row.getDouble("TrpCuk")));
            tblPibTrf.setTrpPPN(NumberUtil.fixDouble(row.getDouble("TrpPpn")));
            tblPibTrf.setTrpPBM(NumberUtil.fixDouble(row.getDouble("TrpPbm")));
            tblPibTrf.setTrpPPH(NumberUtil.fixDouble(row.getDouble("TrpPph")));
            tblPibTrf.setKdTrpBMAD(row.getString("KdTrpBmAD"));
            tblPibTrf.setKdTrpBMTP(row.getString("KdTrpBmTP"));
            tblPibTrf.setTrpBMTP(NumberUtil.fixDouble(row.getDouble("TrpBmTP")));
            tblPibTrf.setKdTrpBMIM(row.getString("KdTrpBmIM"));
            tblPibTrf.setTrpBMIM(NumberUtil.fixDouble(row.getDouble("TrpBmIM")));
            tblPibTrf.setKdTrpBMPB(row.getString("KdTrpBmPB"));
            tblPibTrf.setTrpBMPB(NumberUtil.fixDouble(row.getDouble("TrpBmPB")));
            tblPibTrf.setKdCukSub(row.getString("KDCUKSUB"));
            tblPibTrf.setHjeCuk(NumberUtil.fixDouble(row.getDouble("HJECuk")));
            tblPibTrf.setKdKmsCuk(row.getString("KdKmsCuk"));
            tblPibTrf.setIsiPerKmsCuk(NumberUtil.fixDouble(row.getDouble("IsiPerKmsCuk")));
            tblPibTrf.setFlagTis(row.getString("FlagTis"));
            tblPibTrf.setFlagLekat(row.getString("FlagLekat"));
            tblPibTrfs.add(tblPibTrf);
        }
        pibTrf.getDatabase().close();
        return tblPibTrfs;
    }

    public void save(List<TblPibTrf> tblPibTrfs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibTrf");
        tblPibTrfs.stream().map(tblPibTrf -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibTrf.getCar());
            map.put("NoHs", tblPibTrf.getNoHs());
            map.put("SeriTrp", tblPibTrf.getSeriTrp());
            map.put("KdTrpBm", tblPibTrf.getKdTrpBM());
            map.put("KdSatBm", tblPibTrf.getKdSatBM());
            map.put("TrpBm", tblPibTrf.getTrpBM());
            map.put("KdCuk", tblPibTrf.getKdCuk());
            map.put("KdTrpCuk", tblPibTrf.getKdTrpCuk());
            map.put("KdSatCuk", tblPibTrf.getKdSatCuk());
            map.put("TrpCuk", tblPibTrf.getTrpCuk());
            map.put("TrpPpn", tblPibTrf.getTrpPPN());
            map.put("TrpPbm", tblPibTrf.getTrpPBM());
            map.put("TrpPph", tblPibTrf.getTrpPPH());
            map.put("KdTrpBmAD", tblPibTrf.getKdTrpBMAD());
            map.put("KdTrpBmTP", tblPibTrf.getKdTrpBMTP());
            map.put("TrpBmTP", tblPibTrf.getTrpBMTP());
            map.put("KdTrpBmIM", tblPibTrf.getKdTrpBMIM());
            map.put("TrpBmIM", tblPibTrf.getTrpBMIM());
            map.put("KdTrpBmPB", tblPibTrf.getKdTrpBMPB());
            map.put("TrpBmPB", tblPibTrf.getTrpBMPB());
            map.put("KDCUKSUB", tblPibTrf.getKdCukSub());
            map.put("HJECuk", tblPibTrf.getHjeCuk());
            map.put("KdKmsCuk", tblPibTrf.getKdKmsCuk());
            map.put("IsiPerKmsCuk", tblPibTrf.getIsiPerKmsCuk());
            map.put("FlagTis", tblPibTrf.getFlagTis());
            map.put("FlagLekat", tblPibTrf.getFlagLekat());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
    }

    public List<TblPibTrf> saveAll(List<TblPibTrf> tblPibTrfs) throws IOException {
        List<Map<String, Object>> mapList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibTrf");
        tblPibTrfs.stream().map(tblPibTrf -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibTrf.getCar());
            map.put("NoHs", tblPibTrf.getNoHs());
            map.put("SeriTrp", tblPibTrf.getSeriTrp());
            map.put("KdTrpBm", tblPibTrf.getKdTrpBM());
            map.put("KdSatBm", tblPibTrf.getKdSatBM());
            map.put("TrpBm", tblPibTrf.getTrpBM());
            map.put("KdCuk", tblPibTrf.getKdCuk());
            map.put("KdTrpCuk", tblPibTrf.getKdTrpCuk());
            map.put("KdSatCuk", tblPibTrf.getKdSatCuk());
            map.put("TrpCuk", tblPibTrf.getTrpCuk());
            map.put("TrpPpn", tblPibTrf.getTrpPPN());
            map.put("TrpPbm", tblPibTrf.getTrpPBM());
            map.put("TrpPph", tblPibTrf.getTrpPPH());
            map.put("KdTrpBmAD", tblPibTrf.getKdTrpBMAD());
            map.put("TrpBmAD", tblPibTrf.getTrpBMAD());
            map.put("KdTrpBmTP", tblPibTrf.getKdTrpBMTP());
            map.put("TrpBmTP", tblPibTrf.getTrpBMTP());
            map.put("KdTrpBmIM", tblPibTrf.getKdTrpBMIM());
            map.put("TrpBmIM", tblPibTrf.getTrpBMIM());
            map.put("KdTrpBmPB", tblPibTrf.getKdTrpBMPB());
            map.put("TrpBmPB", tblPibTrf.getTrpBMPB());
            map.put("KDCUKSUB", tblPibTrf.getKdCukSub());
            map.put("HJECuk", tblPibTrf.getHjeCuk());
            map.put("KdKmsCuk", tblPibTrf.getKdKmsCuk());
            map.put("IsiPerKmsCuk", tblPibTrf.getIsiPerKmsCuk());
            map.put("FlagTis", tblPibTrf.getFlagTis());
            map.put("FlagLekat", tblPibTrf.getFlagLekat());
            return map;
        }).forEachOrdered(map -> {
            mapList.add(map);
        });
        jackcess.insertRows(table, mapList);
        return tblPibTrfs;
    }
}

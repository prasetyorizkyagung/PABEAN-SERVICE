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
import com.rtm.pabean.model.bup.module.TblPibRes;
import com.rtm.pabean.utils.DateUtil;
import com.rtm.pabean.utils.JackcessUtil;
import com.rtm.pabean.utils.NumberUtil;

@Repository
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class TblPibResDao {
    
    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblPibRes> getAll() throws IOException {
        List<TblPibRes> tblPibResList = new ArrayList<>();
        Table pibRes = jackcess.getTable(targetDatabase, "tblPibRes");
        for (Row row : pibRes) {
            TblPibRes tblPibRes = new TblPibRes();
            tblPibRes.setCar(row.getString("CAR"));
            tblPibRes.setResKd(row.getString("RESKD"));
            tblPibRes.setResTg(DateUtil.toDate(row.getLocalDateTime("RESTG")));
            tblPibRes.setResWk(row.getString("RESWK"));
            tblPibRes.setDokResNo(row.getString("DOKRESNO"));
            tblPibRes.setDokResTg(DateUtil.toDate(row.getLocalDateTime("DOKRESTG")));
            tblPibRes.setKpbc(row.getString("KPBC"));
            tblPibRes.setPibNo(row.getString("PIBNO"));
            tblPibRes.setPibTg(DateUtil.toDate(row.getLocalDateTime("PIBTG")));
            tblPibRes.setKdGudang(row.getString("KDGUDANG"));
            tblPibRes.setPejabat1(row.getString("PEJABAT1"));
            tblPibRes.setNip1(row.getString("Nip1"));
            tblPibRes.setJabatan1(row.getString("JABATAN1"));
            tblPibRes.setPejabat2(row.getString("PEJABAT2"));
            tblPibRes.setNip2(row.getString("Nip2"));
            tblPibRes.setJatuhTempo(DateUtil.toDate(row.getLocalDateTime("JATUHTEMPO")));
            tblPibRes.setKomTg(DateUtil.toDate(row.getLocalDateTime("KOMTG")));
            tblPibRes.setKomWk(row.getString("KOMWK"));
            tblPibRes.setDeskripsi(row.getString("DesKripsi"));
            tblPibRes.setDibaca(row.getBoolean("dibaca"));
            tblPibRes.setJmKemas(NumberUtil.toBigInteger(row.getBigDecimal("JmKemas")));
            tblPibRes.setNoKemas(row.getString("NoKemas"));
            tblPibRes.setNpwpImp(row.getString("NPWPImp"));
            tblPibRes.setNamaImp(row.getString("NamaImp"));
            tblPibRes.setAlamatImp(row.getString("AlamatImp"));
            tblPibRes.setIdPpjk(row.getString("IDPPJK"));
            tblPibRes.setNamaPpjk(row.getString("NamaPPJK"));
            tblPibRes.setAlamatPpjk(row.getString("AlamatPPJK"));
            tblPibRes.setKodeBill(row.getString("KodeBill"));
            tblPibRes.setTanggalBill(row.getString("TanggalBill"));
            tblPibRes.setTanggalJtTempo(row.getString("TanggalJtTempo"));
            tblPibRes.setTanggalAju(row.getString("TanggalAju"));
            tblPibRes.setTotalBayar(row.getString("TotalBayar"));
            tblPibRes.setTerbilang(row.getString("Terbilang"));
            tblPibResList.add(tblPibRes);
        }
        pibRes.getDatabase().close();
        return tblPibResList;
    }

    public void save(List<TblPibRes> tblPibResList) throws IOException {
        List<Map<String, Object>> mapsList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibRes");
        tblPibResList.stream().map(tblPibRes -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibRes.getCar());
            map.put("RESKD", tblPibRes.getResKd());
            map.put("RESTG", tblPibRes.getResTg());
            map.put("RESWK", tblPibRes.getResWk());
            map.put("DOKRESNO", tblPibRes.getDokResNo());
            map.put("DOKRESTG", tblPibRes.getDokResTg());
            map.put("KPBC", tblPibRes.getKpbc());
            map.put("PIBNO", tblPibRes.getPibNo());
            map.put("PIBTG", tblPibRes.getPibTg());
            map.put("KDGUDANG", tblPibRes.getKdGudang());
            map.put("PEJABAT1", tblPibRes.getPejabat1());
            map.put("Nip1", tblPibRes.getNip1());
            map.put("JABATAN1", tblPibRes.getJabatan1());
            map.put("PEJABAT2", tblPibRes.getPejabat2());
            map.put("Nip2", tblPibRes.getNip2());
            map.put("JATUHTEMPO", tblPibRes.getJatuhTempo());
            map.put("KOMTG", tblPibRes.getKomTg());
            map.put("KOMWK", tblPibRes.getKomWk());
            map.put("DesKripsi", tblPibRes.getDeskripsi());
            map.put("dibaca", tblPibRes.isDibaca());
            map.put("JmKemas", tblPibRes.getJmKemas());
            map.put("NoKemas", tblPibRes.getNoKemas());
            map.put("NPWPImp", tblPibRes.getNpwpImp());
            map.put("NamaImp", tblPibRes.getNamaImp());
            map.put("AlamatImp", tblPibRes.getAlamatImp());
            map.put("IDPPJK", tblPibRes.getIdPpjk());
            map.put("NamaPPJK", tblPibRes.getNamaPpjk());
            map.put("AlamatPPJK", tblPibRes.getAlamatPpjk());
            map.put("KodeBill", tblPibRes.getKodeBill());
            map.put("TanggalBill", tblPibRes.getTanggalBill());
            map.put("TanggalJtTempo", tblPibRes.getTanggalJtTempo());
            map.put("TanggalAju", tblPibRes.getTanggalAju());
            map.put("TotalBayar", tblPibRes.getTotalBayar());
            map.put("Terbilang", tblPibRes.getTerbilang());
            return map;
        }).forEachOrdered(map -> {
            mapsList.add(map);
        });
        jackcess.insertRows(table, mapsList);
    }
    
    public List<TblPibRes> saveAll(List<TblPibRes> tblPibResList) throws IOException {
        List<Map<String, Object>> mapsList = new ArrayList<>();
        Table table = jackcess.getTable(targetDatabase, "tblPibRes");
        tblPibResList.stream().map(tblPibRes -> {
            Map<String, Object> map = new HashMap<>();
            map.put("CAR", tblPibRes.getCar());
            map.put("RESKD", tblPibRes.getResKd());
            map.put("RESTG", tblPibRes.getResTg());
            map.put("RESWK", tblPibRes.getResWk());
            map.put("DOKRESNO", tblPibRes.getDokResNo());
            map.put("DOKRESTG", tblPibRes.getDokResTg());
            map.put("KPBC", tblPibRes.getKpbc());
            map.put("PIBNO", tblPibRes.getPibNo());
            map.put("PIBTG", tblPibRes.getPibTg());
            map.put("KDGUDANG", tblPibRes.getKdGudang());
            map.put("PEJABAT1", tblPibRes.getPejabat1());
            map.put("Nip1", tblPibRes.getNip1());
            map.put("JABATAN1", tblPibRes.getJabatan1());
            map.put("PEJABAT2", tblPibRes.getPejabat2());
            map.put("Nip2", tblPibRes.getNip2());
            map.put("JATUHTEMPO", tblPibRes.getJatuhTempo());
            map.put("KOMTG", tblPibRes.getKomTg());
            map.put("KOMWK", tblPibRes.getKomWk());
            map.put("DesKripsi", tblPibRes.getDeskripsi());
            map.put("dibaca", tblPibRes.isDibaca());
            map.put("JmKemas", tblPibRes.getJmKemas());
            map.put("NoKemas", tblPibRes.getNoKemas());
            map.put("NPWPImp", tblPibRes.getNpwpImp());
            map.put("NamaImp", tblPibRes.getNamaImp());
            map.put("AlamatImp", tblPibRes.getAlamatImp());
            map.put("IDPPJK", tblPibRes.getIdPpjk());
            map.put("NamaPPJK", tblPibRes.getNamaPpjk());
            map.put("AlamatPPJK", tblPibRes.getAlamatPpjk());
            map.put("KodeBill", tblPibRes.getKodeBill());
            map.put("TanggalBill", tblPibRes.getTanggalBill());
            map.put("TanggalJtTempo", tblPibRes.getTanggalJtTempo());
            map.put("TanggalAju", tblPibRes.getTanggalAju());
            map.put("TotalBayar", tblPibRes.getTotalBayar());
            map.put("Terbilang", tblPibRes.getTerbilang());
            return map;
        }).forEachOrdered(map -> {
            mapsList.add(map);
        });
        jackcess.insertRows(table, mapsList);
        return tblPibResList;
    }
}

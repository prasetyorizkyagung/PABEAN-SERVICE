package com.rtm.pabean.dao.bue.module;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.rtm.pabean.model.bue.module.TblModaTambahan;
import com.rtm.pabean.utils.JackcessUtil;
import com.healthmarketscience.jackcess.Row;
import com.healthmarketscience.jackcess.Table;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TblModaTambahanDao {

    @Autowired
    private JackcessUtil jackcess;
    private String targetDatabase;

    public String getTargetDatabase() {
        return targetDatabase;
    }

    public void setTargetDatabase(String targetDatabase) {
        this.targetDatabase = targetDatabase;
    }

    public List<TblModaTambahan> getAll() throws IOException {
        List<TblModaTambahan> modaList = new ArrayList<>();
        Table modaTambahan = jackcess.getTable(targetDatabase, "tblModaTambahan");
        for (Row row : modaTambahan) {
            TblModaTambahan moda = new TblModaTambahan();
            moda.setCar(row.getString("CAR"));
            moda.setSeri(row.getDouble("Seri").intValue());
            moda.setModa(row.getString("Moda"));
            moda.setCarrier(row.getString("carrier"));
            moda.setVoy(row.getString("Voy"));
            moda.setBendera(row.getString("Bendera"));
            modaList.add(moda);
        }
        modaTambahan.getDatabase().close();
        return modaList;
    }
}

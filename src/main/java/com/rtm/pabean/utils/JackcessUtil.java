package com.rtm.pabean.utils;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import com.healthmarketscience.jackcess.Table;
import com.healthmarketscience.jackcess.crypt.CryptCodecProvider;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class JackcessUtil {

    public Table getTable(String targetDatabase, String tableName) throws IOException {
        Database database = new DatabaseBuilder(new File(targetDatabase)).setCodecProvider(new CryptCodecProvider())
                .open();
        return database.getTable(tableName);
    }

    public void insertRow(Table table, Map<String, Object> mapTable) throws IOException {
        try {
            table.addRowFromMap(mapTable);
            table.getDatabase().close();
        } catch (IOException e) {
            table.getDatabase().close();
            throw new IOException(e);
        }
    }

    public void insertRows(Table table, List<Map<String, Object>> listMapTable) throws IOException {
        try {
            table.addRowsFromMaps(listMapTable);
            table.getDatabase().close();
        } catch (IOException e) {
            table.getDatabase().close();
            throw new IOException(e);
        }
    }
}
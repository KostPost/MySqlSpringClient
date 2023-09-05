package com.kostpost.showtable;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Array;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

@Component
public class TableLister {

    private final JdbcTemplate jdbcTemplate;

    public TableLister(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public ArrayList<String> listAllTablesInDatabase(String databaseName) {

        ArrayList<String> Tables = new ArrayList<>();

        try {
            DatabaseMetaData metaData = jdbcTemplate.getDataSource().getConnection().getMetaData();
            ResultSet tables = metaData.getTables(databaseName, null, null, new String[]{"TABLE"});

            while (tables.next()) {

                String tableName = tables.getString("TABLE_NAME");
                String checkTable = "";

                for (int i = tableName.length() - 3; i < tableName.length(); i++) {
                    checkTable += tableName.charAt(i);
                }
                if (!checkTable.equals("seq") && !tableName.equals("table_info")) {
                    Tables.add(tableName);
                    //System.out.println(tableName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Tables;

    }
}


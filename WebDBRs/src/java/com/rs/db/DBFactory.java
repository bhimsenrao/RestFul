package com.rs.db;

import java.sql.DriverManager;

public class DBFactory {

    private static DBFactory factory = new DBFactory();
    private static java.sql.Connection connection = null;

    private DBFactory() {
    }

    public static DBFactory getFactory() {
        return factory;
    }

    public java.sql.Connection getConnection() {
        try {
            java.util.Properties prop = new java.util.Properties();
            prop.load(new java.io.FileReader("E:\\rest\\WebDBRs\\src\\java\\db.properties"));
            Class.forName(prop.getProperty("driver"));
            connection = DriverManager.getConnection(
                    prop.getProperty("url"),
                    prop.getProperty("username"), prop.getProperty("password"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }
}

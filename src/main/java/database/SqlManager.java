package database;

import utils.ProjProperties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlManager {

    public static Connection connection;

    public static void connect() {
        try {
            String url = "jdbc:sqlite:" + ProjProperties.getProperty("db.name");
            connection = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

}

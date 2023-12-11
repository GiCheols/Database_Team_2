package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private static final String dbUrl
            = "jdbc:mysql://localhost:3306/db_2?serverTimezone=Asia/Seoul&useSSL=false&useUnicode=true&characterEncoding=utf8";
    private static final String userName = "root";
    private static final String password = "hansung";
    private static Connection c;

    public static Connection getConnection(){
        try {
            c = DriverManager.getConnection(dbUrl, userName, password);
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return c;
    }
}

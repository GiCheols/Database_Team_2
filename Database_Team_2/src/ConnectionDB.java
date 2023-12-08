import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    private String dbUrl;
    private String userName;
    private String password;
    private Connection c = null;

    public ConnectionDB(String dbUrl, String userName, String password) {
        this.dbUrl = dbUrl;
        this.userName = userName;
        this.password = password;
    }

    public Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.err.println("JDBC DRIVER ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        try {
            c = DriverManager.getConnection(dbUrl, userName, password);
            System.out.println("CONNECT TO MySQL");
        } catch (SQLException e) {
            System.out.println("CONNECTION ERROR: " + e.getMessage());
            e.printStackTrace();
        }

        return c;
    }
}

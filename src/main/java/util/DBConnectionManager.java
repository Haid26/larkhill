package util;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//import org.h2.Driver;
public class DBConnectionManager {

    private Connection connection;

    public DBConnectionManager(String dbURL, String user, String pwd) throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        this.connection = DriverManager.getConnection(dbURL, user, pwd);
        if (this.connection == null)
            System.err.print("Connection failed olololo");

    }

    public Connection getConnection(){
        return this.connection;
    }
}
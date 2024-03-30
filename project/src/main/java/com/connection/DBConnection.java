package com.connection;
import java.sql.*;

public class DBConnection {

    private final String url = "jdbc:mysql://localhost:3306/cricketdatabase";
    private final String userName = "root";
    private final String password = "root";
    
    private Connection connection;

    public Connection getConnection() throws SQLException {
        
//    	if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(url, userName, password);
//        }
        return connection;
    }
}

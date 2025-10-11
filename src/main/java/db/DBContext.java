/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class DBContext {

    private Connection conn;
    private final String DB_URL = "jdbc:sqlserver://127.0.0.1:1433;databaseName=RestaurantManagement;encrypt=false";
    private final String DB_USER = "sa";
    private final String DB_PWD = "6789";

    public DBContext() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            this.conn = (Connection) DriverManager.getConnection(DB_URL, DB_USER, DB_PWD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBContext.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return conn;
    }
    
    public ResultSet executeSelectionQuery(String query, Object[] params) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement(query);

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
            }
        }

        return statement.executeQuery();
    }

    public int executeQuery(String query, Object[] params) throws SQLException {
        PreparedStatement statement = this.getConnection().prepareStatement(query);

        if (params != null) {
            for (int i = 0; i < params.length; i++) {
                statement.setObject(i + 1, params[i]);
                
//                System.out.println(params[i]);
            }
        }

        return statement.executeUpdate();
    }

}

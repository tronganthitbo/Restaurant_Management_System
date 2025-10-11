/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static constant.CommonFunction.checkErrorSQL;
import static constant.Constants.MAX_ELEMENTS_PER_PAGE;
import db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Table;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class TableDAO extends DBContext {

    public static void main(String[] args) {
    }

    public List<Table> getAll() {
        List<Table> list = new ArrayList<>();

        try {
            String query = "SELECT table_id, table_number, table_capacity, status\n"
                    + "FROM     [table]\n"
                    + "WHERE  (LOWER(status) = LOWER(N'Active'))\n"
                    + "ORDER BY table_id";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {
                int table_id = rs.getInt(1);
                String table_number = rs.getString(2);
                int table_capacity = rs.getInt(3);
                String status = rs.getString(4);

                Table item = new Table(table_id, table_number, table_capacity, status);

                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Table> getAll(int page) {
        List<Table> list = new ArrayList<>();

        try {
            String query = "SELECT table_id, table_number, table_capacity, status\n"
                    + "FROM     [table]\n"
                    + "WHERE  (LOWER(status) = LOWER(N'Active'))\n"
                    + "ORDER BY table_id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int table_id = rs.getInt(1);
                String table_number = rs.getString(2);
                int table_capacity = rs.getInt(3);
                String status = rs.getString(4);

                Table item = new Table(table_id, table_number, table_capacity, status);

                list.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Table getElementByID(int id) {

        try {
            String query = "SELECT table_id, table_number, table_capacity, status\n"
                    + "FROM     [table]\n"
                    + "WHERE  (table_id = ? and  LOWER(status) = LOWER(N'Active'))\n"
                    + "ORDER BY table_id\n";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {
                int table_id = rs.getInt(1);
                String table_number = rs.getString(2);
                int table_capacity = rs.getInt(3);
                String status = rs.getString(4);

                Table item = new Table(table_id, table_number, table_capacity, status);

                return item;
            }
        } catch (SQLException ex) {
            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int create(String table_number, int table_capacity) {
        try {
            String query = "INSERT INTO [table]\n"
                    + "                  (table_number, table_capacity, status)\n"
                    + "VALUES (?, ?, ?)";

            return this.executeQuery(query, new Object[]{table_number, table_capacity, "Active"});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int edit(int table_id, String table_number, int table_capacity) {
        try {

            String query = "UPDATE [table]\n"
                    + "SET          table_number = ?, table_capacity = ?\n"
                    + "WHERE  (table_id = ?)";

            return this.executeQuery(query, new Object[]{table_number, table_capacity, table_id});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(TableDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int delete(int id) {
        try {
            String query = "UPDATE [table]\n"
                    + "SET          status = 'Deleted'\n"
                    + "WHERE  (table_id = ?)";

            return this.executeQuery(query, new Object[]{id});

        } catch (SQLException ex) {

        }
        return -1;
    }

    public int countItem() {
        try {
            String query = "select count(table_id) as numrow from [dbo].[table]";
            ResultSet rs = this.executeSelectionQuery(query, null);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("Error");
        }

        return 0;
    }
}

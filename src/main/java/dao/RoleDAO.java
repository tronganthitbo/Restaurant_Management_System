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
import model.Role;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class RoleDAO extends DBContext {

    public static void main(String[] args) {
    }

    public List<Role> getAll() {
        List<Role> list = new ArrayList<>();

        try {
            String query = "SELECT role_id, role_name, description, status\n"
                    + "FROM     role\n"
                    + "ORDER BY role_id\n";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {

                int role_id = rs.getInt(1);
                String role_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Role role = new Role(role_id, role_name, description, status);

                list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Role> getAll(int page) {
        List<Role> list = new ArrayList<>();

        try {
            String query = "SELECT role_id, role_name, description, status\n"
                    + "FROM     role\n"
                    + "ORDER BY role_id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int role_id = rs.getInt(1);
                String role_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Role role = new Role(role_id, role_name, description, status);

                list.add(role);
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Role getElementByID(int id) {

        try {
            String query = "SELECT role_id, role_name, description, status\n"
                    + "FROM     role\n"
                    + "WHERE  (role_id = ?)\n";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {
                int role_id = rs.getInt(1);
                String role_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Role role = new Role(role_id, role_name, description, status);

                return role;
            }
        } catch (SQLException ex) {
            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int create(String role_name, String description, String status) {
        try {
            String query = "INSERT INTO role (role_name, description, status)\n"
                    + "VALUES (?, ?, ?)";

            return this.executeQuery(query, new Object[]{role_name, description, status});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int update(int role_id, String role_name, String description, String status) {
        try {

            String query = "UPDATE role\n"
                    + "SET role_name = ?, description = ?, status = ?\n"
                    + "WHERE  (role_id = ?)";

            return this.executeQuery(query, new Object[]{role_name, description, status, role_id});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(RoleDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countItem() {
        try {
            String query = "select count(role_id) as numrow from [dbo].[Role]";
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

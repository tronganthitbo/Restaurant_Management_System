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
import model.Employee;

/**
 *
 * @author PHAT
 */
public class EmployeeDAO extends DBContext {
    public List<Employee> getAll() {
        List<Employee> list = new ArrayList<>();
        try {
            String query = "SELECT emp_id, emp_account, password, emp_name, gender, dob, "
                    + "phone_number, email, address, role_id, status "
                    + "FROM Employee WHERE LOWER(status) <> 'deleted' ORDER BY emp_id";
            ResultSet rs = this.executeSelectionQuery(query, null);
            while (rs.next()) {
                int emp_id = rs.getInt(1);
                String emp_account = rs.getString(2);
                String password = rs.getString(3);
                String emp_name = rs.getString(4);
                String gender = rs.getString(5);
                java.sql.Date dob = rs.getDate(6);
                String phone_number = rs.getString(7);
                String email = rs.getString(8);
                String address = rs.getString(9);
                int role_id = rs.getInt(10);
                String status = rs.getString(11);

                Employee emp = new Employee(emp_id, emp_account, password, emp_name, gender, dob,
                        phone_number, email, address, role_id, status);
                list.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public List<Employee> getAll(int page) {
        List<Employee> list = new ArrayList<>();
        try {
            String query = "SELECT emp_id, emp_account, password, emp_name, gender, dob, "
                    + "phone_number, email, address, role_id, status "
                    + "FROM Employee WHERE LOWER(status) <> 'deleted' ORDER BY emp_id"
                    + "OFFSET ? ROWS FETCH NEXT ? ROWS ONLY;";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});
            while (rs.next()) {
                int emp_id = rs.getInt(1);
                String emp_account = rs.getString(2);
                String password = rs.getString(3);
                String emp_name = rs.getString(4);
                String gender = rs.getString(5);
                java.sql.Date dob = rs.getDate(6);
                String phone_number = rs.getString(7);
                String email = rs.getString(8);
                String address = rs.getString(9);
                int role_id = rs.getInt(10);
                String status = rs.getString(11);

                Employee emp = new Employee(emp_id, emp_account, password, emp_name, gender, dob,
                        phone_number, email, address, role_id, status);
                list.add(emp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public Employee getElementByID(int id) {
        try {
            String query = "SELECT emp_id, emp_account, password, emp_name, gender, dob, "
                    + "phone_number, email, address, role_id, status "
                    + "FROM Employee WHERE emp_id = ? AND LOWER(status) <> 'deleted'";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});
            while (rs.next()) {
                int emp_id = rs.getInt(1);
                String emp_account = rs.getString(2);
                String password = rs.getString(3);
                String emp_name = rs.getString(4);
                String gender = rs.getString(5);
                java.sql.Date dob = rs.getDate(6);
                String phone_number = rs.getString(7);
                String email = rs.getString(8);
                String address = rs.getString(9);
                int role_id = rs.getInt(10);
                String status = rs.getString(11);

                Employee emp = new Employee(emp_id, emp_account, password, emp_name, gender, dob,
                        phone_number, email, address, role_id, status);
                return emp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int create(String emp_account, String password, String emp_name, String gender, java.sql.Date dob,
            String phone_number, String email, String address, int role_id) {
        try {
            String query = "INSERT INTO Employee (emp_account, password, emp_name, gender, dob, "
                    + "phone_number, email, address, role_id, status) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            return this.executeQuery(query,
                    new Object[]{emp_account, password, emp_name, gender, dob, phone_number, email, address, role_id, "Active"});
        } catch (SQLException ex) {
            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int edit(int emp_id, String emp_account, String password, String emp_name, String gender, java.sql.Date dob,
            String phone_number, String email, String address, int role_id, String status) {
        try {
            String query = "UPDATE Employee SET emp_account = ?, password = ?, emp_name = ?, gender = ?, dob = ?, "
                    + "phone_number = ?, email = ?, address = ?, role_id = ?, status = ? "
                    + "WHERE emp_id = ?";
            return this.executeQuery(query, new Object[]{emp_account, password, emp_name, gender, dob, phone_number,
                email, address, role_id, status, emp_id});
        } catch (SQLException ex) {
            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int delete(int id) {
        try {
            String query = "UPDATE Employee SET status = 'Deleted' WHERE emp_id = ?";
            return this.executeQuery(query, new Object[]{id});
        } catch (SQLException ex) {
            Logger.getLogger(EmployeeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int countItem() {
        try {
            String query = "SELECT COUNT(emp_id) AS numrow FROM Employee WHERE LOWER(status) = LOWER(N'Active')";
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

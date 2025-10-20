/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static constant.CommonFunction.checkErrorSQL;
import static constant.Constants.MAX_ELEMENTS_PER_PAGE;
import db.DBContext;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Import;

/**
 *
 * @author TruongBinhTrong
 */
public class ImportDAO extends DBContext {

    public List<Import> getAll() {
        List<Import> list = new ArrayList<>();

        try {
            String query = "SELECT i.import_id, igd.ingredient_name, id.quantity, id.unit_price, id.total_price, e.emp_name, s.contact_person, s.supplier_name, i.import_date FROM import i \n"
                    + "JOIN import_detail id ON i.import_id = id.import_id \n"
                    + "JOIN ingredient igd ON id.ingredient_id = igd.ingredient_id\n"
                    + "JOIN employee e ON e.emp_id = i.emp_id\n"
                    + "JOIN supplier s ON s.supplier_id = i.supplier_id";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {
                int importId = rs.getInt(1);
                String ingredientName = rs.getString(2);
                int quantity = rs.getInt(3);
                int unitPrice = rs.getInt(4);
                int totalPrice = rs.getInt(5);
                String empName = rs.getString(6);
                String contactPerson = rs.getString(7);
                String supplierName = rs.getString(8);
                Date importDate = rs.getDate(9);

                Import imp = new Import(importId, ingredientName, quantity, unitPrice, totalPrice, empName, contactPerson, supplierName, importDate);

                list.add(imp);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Category> getAll(int page) {
        List<Category> list = new ArrayList<>();

        try {
            String query = "SELECT category_id, category_name, description, status\n"
                    + "FROM     category\n"
                    + "WHERE  (LOWER(status) = LOWER(N'Active'))\n"
                    + "ORDER BY category_id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int categoryId = rs.getInt(1);
                String categoryName = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Category category = new Category(categoryId, categoryName, description, status);

                list.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Import getElementByID(int id) {

        try {
            String query = "SELECT i.import_id, igd.ingredient_name, id.quantity, id.unit_price, id.total_price, e.emp_name, s.contact_person, s.supplier_name, i.import_date FROM import i \n"
                    + "JOIN import_detail id ON i.import_id = id.import_id \n"
                    + "JOIN ingredient igd ON id.ingredient_id = igd.ingredient_id\n"
                    + "JOIN employee e ON e.emp_id = i.emp_id\n"
                    + "JOIN supplier s ON s.supplier_id = i.supplier_id"
                    + "WHERE  (import_id = ? and LOWER(status) = LOWER(N'Active'))\n";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {
                int importId = rs.getInt(1);
                String ingredientName = rs.getString(2);
                int quantity = rs.getInt(3);
                int unitPrice = rs.getInt(4);
                int totalPrice = rs.getInt(5);
                String empName = rs.getString(6);
                String contactPerson = rs.getString(7);
                String supplierName = rs.getString(8);
                Date importDate = rs.getDate(9);

                Import imp = new Import(importId, ingredientName, quantity, unitPrice, totalPrice, empName, contactPerson, supplierName, importDate);


                return imp;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int add(String categoryName, String description) {
        try {
            String query = "INSERT INTO category (category_name, description, status)\n"
                    + "VALUES (?, ?, ?)";

            return this.executeQuery(query, new Object[]{categoryName, description, "Active"});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int edit(int categoryId, String categoryName, String description) {
        try {

            String query = "UPDATE category\n"
                    + "SET category_name = ?, description = ?\n"
                    + "WHERE  (category_id = ?)";

            return this.executeQuery(query, new Object[]{categoryName, description, categoryId});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int delete(int id) {
        try {
            String query = "UPDATE category\n"
                    + "SET status = 'Deleted'\n"
                    + "WHERE  (category_id = ?)";

            return this.executeQuery(query, new Object[]{id});

        } catch (SQLException ex) {

        }
        return -1;
    }

    public int countItem() {
        try {
            String query = "select count(category_id) as numrow from [dbo].[category]";
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

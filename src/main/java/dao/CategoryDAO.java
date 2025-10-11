package dao;

import static constant.CommonFunction.checkErrorSQL;
import static constant.Constants.MAX_ELEMENTS_PER_PAGE;
import db.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;


public class CategoryDAO extends DBContext {

    public static void main(String[] args) {
    }

    public List<Category> getAll() {
        List<Category> list = new ArrayList<>();

        try {
            String query = "SELECT category_id, category_name, description, status\n"
                    + "FROM     category\n"
                    + "WHERE  (LOWER(status) = LOWER(N'Active'))\n"
                    + "ORDER BY category_id";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {

                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Category category = new Category(category_id, category_name, description, status);

                list.add(category);
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
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Category category = new Category(category_id, category_name, description, status);

                list.add(category);
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Category getElementByID(int id) {

        try {
            String query = "SELECT category_id, category_name, description, status\n"
                    + "FROM     category\n"
                    + "WHERE  (category_id = ? and LOWER(status) = LOWER(N'Active'))\n";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {
                int category_id = rs.getInt(1);
                String category_name = rs.getString(2);
                String description = rs.getString(3);
                String status = rs.getString(4);

                Category category = new Category(category_id, category_name, description, status);

                return category;
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int create(String category_name, String description) {
        try {
            String query = "INSERT INTO category (category_name, description, status)\n"
                    + "VALUES (?, ?, ?)";

            return this.executeQuery(query, new Object[]{category_name, description, "Active"});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) {
                return sqlError;
            }

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int edit(int category_id, String category_name, String description) {
        try {

            String query = "UPDATE category\n"
                    + "SET category_name = ?, description = ?\n"
                    + "WHERE  (category_id = ?)";

            return this.executeQuery(query, new Object[]{category_name, description, category_id});

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



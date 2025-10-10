/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import static constant.Constants.*;
import static constant.CommonFunction.*;
import db.DBContext;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.UserAccount;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class UserAccountDAO extends DBContext {

    public static void main(String[] args) {
    }

    public List<UserAccount> getAll() {
        List<UserAccount> list = new ArrayList<>();

        try {
            String query = "SELECT UserAccount.user_id, UserAccount.username, UserAccount.password, UserAccount.full_name, UserAccount.email, UserAccount.phone, UserAccount.role_id, Role.role_name, UserAccount.status, UserAccount.avatar\n"
                    + "FROM UserAccount INNER JOIN\n"
                    + "Role ON UserAccount.role_id = Role.role_id";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String full_name = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role_id = rs.getInt(7);
                String role = rs.getString(8);
                String status = rs.getString(9);
                String avatar = rs.getString(10);

                UserAccount account = new UserAccount(user_id, username, password, full_name, email, phone, role_id, role, status, avatar);

                list.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<UserAccount> getAll(int page) {
        List<UserAccount> list = new ArrayList<>();

        try {
            String query = "SELECT UserAccount.user_id, UserAccount.username, UserAccount.password, UserAccount.full_name, UserAccount.email, UserAccount.phone, UserAccount.role_id, Role.role_name, UserAccount.status, UserAccount.avatar\n"
                    + "FROM UserAccount INNER JOIN\n"
                    + "Role ON UserAccount.role_id = Role.role_id\n"
                    + "order by UserAccount.user_id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String full_name = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role_id = rs.getInt(7);
                String role = rs.getString(8);
                String status = rs.getString(9);
                String avatar = rs.getString(10);

                UserAccount account = new UserAccount(user_id, username, password, full_name, email, phone, role_id, role, status, avatar);

                list.add(account);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public UserAccount getElementByID(int id) {

        try {
            String query = "SELECT UserAccount.user_id, UserAccount.username, UserAccount.password, UserAccount.full_name, UserAccount.email, UserAccount.phone, UserAccount.role_id, Role.role_name, UserAccount.status, UserAccount.avatar\n"
                    + "FROM UserAccount INNER JOIN\n"
                    + "Role ON UserAccount.role_id = Role.role_id\n"
                    + "WHERE UserAccount.user_id = ?";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {

                int user_id = rs.getInt(1);
                String username = rs.getString(2);
                String password = rs.getString(3);
                String full_name = rs.getString(4);
                String email = rs.getString(5);
                String phone = rs.getString(6);
                int role_id = rs.getInt(7);
                String role = rs.getString(8);
                String status = rs.getString(9);
                String avatar = rs.getString(10);

                UserAccount account = new UserAccount(user_id, username, password, full_name, email, phone, role_id, role, status, avatar);
                
                return account;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

//    public int create(String promo_code, String promo_name, String discount_type, int discount_value, int quantity, String start_date, String end_date) {
//        try {
//            String query = "INSERT INTO [dbo].[Promotion]\n"
//                    + "           ([promo_code],[promo_name],[discount_type],[discount_value],[quantity],[start_date],[end_date])\n"
//                    + "     VALUES (?,?,?,?,?,?,?)";
//
//            return this.executeQuery(query, new Object[]{promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date});
//
//        } catch (SQLException ex) {
//
//            int sqlError = checkErrorSQL(ex);
//            if (sqlError != 0) {
//                return sqlError;
//            }
//
//            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
//    }
//
//    public int update(int promo_id, String promo_code, String promo_name, String discount_type, int discount_value, int quantity, String start_date, String end_date) {
//        try {
//
//            String query = "UPDATE [dbo].[Promotion]\n"
//                    + "   SET [promo_code] = ?\n"
//                    + "      ,[promo_name] = ?\n"
//                    + "      ,[discount_type] = ?\n"
//                    + "      ,[discount_value] = ?\n"
//                    + "      ,[quantity] = ?\n"
//                    + "      ,[start_date] = ?\n"
//                    + "      ,[end_date] = ?\n"
//                    + "WHERE promo_id = ?;";
//
//            return this.executeQuery(query, new Object[]{promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date, promo_id});
//
//        } catch (SQLException ex) {
//
//            int sqlError = checkErrorSQL(ex);
//            if (sqlError != 0) {
//                return sqlError;
//            }
//
//            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
//    }
//
//    public int remove(int id) {
//        try {
//            String query = "DELETE FROM [dbo].[Promotion]\n"
//                    + "      WHERE promo_id = ?";
//
//            return this.executeQuery(query, new Object[]{id});
//
//        } catch (SQLException ex) {
//            Logger.getLogger(UserAccountDAO.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        return -1;
//    }

    public int countItem() {
        try {
            String query = "select count(promo_id) as numrow from [dbo].[Promotion]";
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

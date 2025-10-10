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
import model.Promotion;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class PromotionDAO extends DBContext {

//    int idTop = -1;
    public static void main(String[] args) {
//        PromotionDAO dao = new PromotionDAO();
//        
//        List<Promotion> list = dao.getAll();
//        System.out.println(list.size());
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println(list.get(i).getPromo_code());
//        }

//        for (int i = 1; i < 10; i++) {
//            System.out.println(getVNDString(String.valueOf((int) Math.pow(10, i))));
////            System.out.println(String.valueOf((int)Math.pow(10, i)));
//        }
//            System.out.println(dao.countItem());
//        System.out.println(getVNDString("10000"));
//        System.out.println("2025-07-24 19:45:34.0".compareTo("2025-07-24 19:45:00.0"));
//        List<Sale> salesList = dao.getAll();
//
//        for (Sale sale : salesList) {
//            System.out.println(sale);
//        }
//        System.out.println(dao.stringConvertDateTime("2025-06-21T20:00"));
//        System.out.println(dao.create("Minh Nhu", 100, 0, 10, true, "2025-06-21T20:00", "2025-06-21T09:00"));
//        Sale{id=0, name=, discount=, typeOfDiscount=0, amount=10, coHanSuDung=false, dateStart=, dateEnd=}
    }

    public List<Promotion> getAll() {
        List<Promotion> list = new ArrayList<>();

        try {
            String query = "SELECT [promo_id],[promo_code],[promo_name],[discount_type],[discount_value],[quantity],[start_date],[end_date]\n"
                    + "FROM [restaurant_manager].[dbo].[Promotion]";

            ResultSet rs = this.executeSelectionQuery(query, null);

            while (rs.next()) {
                int promo_id = rs.getInt(1);
                String promo_code = rs.getString(2);
                String promo_name = rs.getString(3);
                String discount_type = rs.getString(4);
                int discount_value = rs.getInt(5);
                int quantity = rs.getInt(6);
                String start_date = rs.getString(7);
                String end_date = rs.getString(8);

                Promotion promotion = new Promotion(promo_id, promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date);

                list.add(promotion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public List<Promotion> getAll(int page) {
        List<Promotion> list = new ArrayList<>();

        try {
            String query = "SELECT [promo_id],[promo_code],[promo_name],[discount_type],[discount_value],[quantity],[start_date],[end_date]\n"
                    + "FROM [restaurant_manager].[dbo].[Promotion]\n"
                    + "order by promo_id\n"
                    + "OFFSET ? ROWS \n"
                    + "FETCH NEXT ? ROWS ONLY;";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * MAX_ELEMENTS_PER_PAGE, MAX_ELEMENTS_PER_PAGE});

            while (rs.next()) {
                int promo_id = rs.getInt(1);
                String promo_code = rs.getString(2);
                String promo_name = rs.getString(3);
                String discount_type = rs.getString(4);
                int discount_value = rs.getInt(5);
                int quantity = rs.getInt(6);
                String start_date = rs.getString(7);
                String end_date = rs.getString(8);

                Promotion promotion = new Promotion(promo_id, promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date);

                list.add(promotion);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return list;
    }

    public Promotion getElementByID(int id) {

        try {
            String query = "SELECT [promo_id],[promo_code],[promo_name],[discount_type],[discount_value],[quantity],[start_date],[end_date]\n"
                    + "FROM [restaurant_manager].[dbo].[Promotion]\n"
                    + "WHERE promo_id = ?";

            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});

            while (rs.next()) {

                int promo_id = rs.getInt(1);
                String promo_code = rs.getString(2);
                String promo_name = rs.getString(3);
                String discount_type = rs.getString(4);
                int discount_value = rs.getInt(5);
                int quantity = rs.getInt(6);
                String start_date = rs.getString(7);
                String end_date = rs.getString(8);

                return new Promotion(promo_id, promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date);
            }
        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public int create(String promo_code, String promo_name, String discount_type, int discount_value, int quantity, String start_date, String end_date) {
        try {
            String query = "INSERT INTO [dbo].[Promotion]\n"
                    + "           ([promo_code],[promo_name],[discount_type],[discount_value],[quantity],[start_date],[end_date])\n"
                    + "     VALUES (?,?,?,?,?,?,?)";

            return this.executeQuery(query, new Object[]{promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date});

        } catch (SQLException ex) {

            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) return sqlError;

            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int update(int promo_id, String promo_code, String promo_name, String discount_type, int discount_value, int quantity, String start_date, String end_date) {
        try {

            String query = "UPDATE [dbo].[Promotion]\n"
                    + "   SET [promo_code] = ?\n"
                    + "      ,[promo_name] = ?\n"
                    + "      ,[discount_type] = ?\n"
                    + "      ,[discount_value] = ?\n"
                    + "      ,[quantity] = ?\n"
                    + "      ,[start_date] = ?\n"
                    + "      ,[end_date] = ?\n"
                    + "WHERE promo_id = ?;";

            return this.executeQuery(query, new Object[]{promo_code, promo_name, discount_type, discount_value, quantity, start_date, end_date, promo_id});

        } catch (SQLException ex) {
            
            int sqlError = checkErrorSQL(ex);
            if (sqlError != 0) return sqlError;
            
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public int remove(int id) {
        try {
            String query = "DELETE FROM [dbo].[Promotion]\n"
                    + "      WHERE promo_id = ?";

            return this.executeQuery(query, new Object[]{id});

        } catch (SQLException ex) {
            Logger.getLogger(PromotionDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
    
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import db.DBContext;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Voucher;

/**
 *
 * @author PHAT
 */
public class VoucherDAO extends DBContext {
    public List<Voucher> getAll(int page, int pageSize) {
        List<Voucher> list = new ArrayList<>();
        try {
            String query = "SELECT voucher_id, voucher_code, voucher_name, discount_type, discount_value, quantity, start_date, end_date, status "
                         + "FROM Voucher WHERE LOWER(status) <> 'deleted' "
                         + "ORDER BY voucher_id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
            ResultSet rs = this.executeSelectionQuery(query, new Object[]{(page - 1) * pageSize, pageSize});
            while (rs.next()) {
                Voucher v = new Voucher(
                    rs.getInt("voucher_id"),
                    rs.getString("voucher_code"),
                    rs.getString("voucher_name"),
                    rs.getString("discount_type"),
                    rs.getBigDecimal("discount_value"),
                    rs.getInt("quantity"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("status")
                );
                list.add(v);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public int create(String code, String name, String type, BigDecimal value, int qty, Date start, Date end, String status) {
        try {
            String query = "INSERT INTO Voucher (voucher_code, voucher_name, discount_type, discount_value, quantity, start_date, end_date, status) "
                         + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            return this.executeQuery(query, new Object[]{code, name, type, value, qty, start, end, status});
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int edit(int id, String code, String name, String type, BigDecimal value, int qty, Date start, Date end, String status) {
        try {
            String query = "UPDATE Voucher SET voucher_code=?, voucher_name=?, discount_type=?, discount_value=?, quantity=?, start_date=?, end_date=?, status=? "
                         + "WHERE voucher_id=?";
            return this.executeQuery(query, new Object[]{code, name, type, value, qty, start, end, status, id});
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public int delete(int id) {
        try {
            String query = "UPDATE Voucher SET status='Deleted' WHERE voucher_id=?";
            return this.executeQuery(query, new Object[]{id});
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    public Voucher getById(int id) {
        try {
            String query = "SELECT voucher_id, voucher_code, voucher_name, discount_type, discount_value, quantity, start_date, end_date, status "

                         + "FROM Voucher WHERE voucher_id = ? AND LOWER(status) <> 'deleted'";


            ResultSet rs = this.executeSelectionQuery(query, new Object[]{id});
            if (rs.next()) {
                return new Voucher(
                    rs.getInt("voucher_id"),
                    rs.getString("voucher_code"),
                    rs.getString("voucher_name"),
                    rs.getString("discount_type"),
                    rs.getBigDecimal("discount_value"),
                    rs.getInt("quantity"),
                    rs.getDate("start_date"),
                    rs.getDate("end_date"),
                    rs.getString("status")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}

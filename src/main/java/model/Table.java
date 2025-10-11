/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Dai Minh Nhu - CE190213
 */
public class Table {

    private int table_id;
    private String table_number;
    private int table_capacity;
    private String status;

    public Table(int table_id, String table_number, int table_capacity, String status) {
        this.table_id = table_id;
        this.table_number = table_number;
        this.table_capacity = table_capacity;
        this.status = status;
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public String getTable_number() {
        return table_number;
    }

    public void setTable_number(String table_number) {
        this.table_number = table_number;
    }

    public int getTable_capacity() {
        return table_capacity;
    }

    public void setTable_capacity(int table_capacity) {
        this.table_capacity = table_capacity;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    

}

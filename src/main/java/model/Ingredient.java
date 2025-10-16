/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author TruongBinhTrong
 */
public class Ingredient {
    private int ingredientId;
    private String ingredientName;
    private String typeName;
    private String status;

    public Ingredient() {
    }

    public Ingredient(int ingredientId, String ingredientName, String typeName, String status) {
        this.ingredientId = ingredientId;
        this.ingredientName = ingredientName;
        this.typeName = typeName;
        this.status = status;
    }

    public int getIngredientId() {
        return ingredientId;
    }

    public void setIngredientId(int ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }

    public void setIngredientName(String ingredientName) {
        this.ingredientName = ingredientName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
 
}

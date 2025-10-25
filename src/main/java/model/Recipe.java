/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author PHAT
 */
public class Recipe {

    private int recipeId;
    private int menuItemId;
    private String status;
    private List<RecipeItem> items;

    public Recipe() {
        items = new ArrayList<>();
    }

    public Recipe(int recipeId, int menuItemId, String status) {
        this.recipeId = recipeId;
        this.menuItemId = menuItemId;
        this.status = status;
        this.items = new ArrayList<>();
    }

    // getters & setters
    public int getRecipeId() {
        return recipeId;
    }

    public void setRecipeId(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getMenuItemId() {
        return menuItemId;
    }

    public void setMenuItemId(int menuItemId) {
        this.menuItemId = menuItemId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<RecipeItem> getItems() {
        return items;
    }

    public void setItems(List<RecipeItem> items) {
        this.items = items;
    }

    public void addItem(RecipeItem item) {
        if (items == null) items = new ArrayList<>();
        items.add(item);
    }
    
}

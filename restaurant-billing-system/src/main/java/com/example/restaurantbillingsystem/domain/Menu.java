package com.example.restaurantbillingsystem.domain;

import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.stereotype.Component;

@Table
public class Menu implements Persistable<Long>{

    @Id
    private Long menuId;
    @NotNull
    @Size(min = 5, message = "Title must be at least 5 characters long")
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", message = "must be greater than or equal to 0.0")
    private double price;
    @NotNull(message = "null")
    private Type type;
    
    public Menu() {
    }
    
    public Menu(long menuId, String title, double price, Type type) {
        this.menuId = menuId;
        this.title = title;
        this.price = price;
        this.type = type;
    }
    
    public enum Type {
        SOUP, APPETIZER, SALAD, MAIN, DESSERT
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuId, price, title, type);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Menu other = (Menu) obj;
        return Objects.equals(menuId, other.menuId) && Objects.equals(price, other.price) && Objects.equals(title, other.title)
                && type == other.type;
    }

    @Override
    public String toString() {
        return "Menu [menuId=" + menuId + ", title=" + title + ", price=" + price + ", type=" + type + "]";
    }

    @Override
    public Long getId() {
        return getMenuId();
    }

    @Override
    public boolean isNew() {
        if(menuId != null) {
            return false;
        }
        return true;
    }
}

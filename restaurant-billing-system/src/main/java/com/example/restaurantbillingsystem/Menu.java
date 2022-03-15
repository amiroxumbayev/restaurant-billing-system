package com.example.restaurantbillingsystem;

import java.util.Objects;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Menu {

    private Long id;
    @NotNull
    @Size(min=5, message="Title must be at least 5 characters long")
    private String title;
    @NotNull
    @DecimalMin(value = "0.0", message = "must be greater than or equal to 0.0")
    private double price;
    @NotNull(message = "null")
    private Type type;

    public Menu() {}

    public Menu(long id, String title, double price, Type type) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, price, title, type);
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
        return Objects.equals(id, other.id) && Objects.equals(price, other.price) && Objects.equals(title, other.title)
                && type == other.type;
    }

    @Override
    public String toString() {
        return "Menu [id=" + id + ", title=" + title + ", price=" + price + ", type=" + type + "]";
    }
}

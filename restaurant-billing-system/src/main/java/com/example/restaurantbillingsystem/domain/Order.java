package com.example.restaurantbillingsystem.domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class Order implements Persistable<Long>{

    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    private Date placedAt = new Date();
    @NotNull
    private Status status;

    @Max(message = "Maximum number of table is 20", value = 20)
    @Min(message = "Minimum number of table is 1", value = 1)
    private int tableNumber;

    @NotNull
    private double totalPrice;

    @NotNull
    @Size(min = 1, message = "You must choose at least 1 item")
    @MappedCollection(idColumn = "order_id", keyColumn = "id")
    private List<MenuRef> menus = new ArrayList<>();

    public Order() {
    }

    public Order(Long id, Date placedAt, Status status, int tableNumber, double totalPrice, List<MenuRef> menus) {
        this.id = id;
        this.placedAt = placedAt;
        this.status = status;
        this.tableNumber = tableNumber;
        this.totalPrice = totalPrice;
        this.menus = menus;
    }

    public void addMenu(Menu menu) {
        menus.add(new MenuRef(menu.getMenuId()));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPlacedAt() {
        return placedAt;
    }

    public void setPlacedAt(Date placedAt) {
        this.placedAt = placedAt;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public int getTableNumber() {
        return tableNumber;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<MenuRef> getMenus() {
        return menus;
    }

    public void setMenus(List<MenuRef> menus) {
        this.menus = menus;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menus, placedAt, tableNumber, totalPrice);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Order other = (Order) obj;
        return Objects.equals(id, other.id) && Objects.equals(menus, other.menus)
                && Objects.equals(placedAt, other.placedAt) && tableNumber == other.tableNumber
                && Double.doubleToLongBits(totalPrice) == Double.doubleToLongBits(other.totalPrice);
    }

    @Override
    public String toString() {
        return "Order [id=" + id + ", placedAt=" + placedAt + ", tableNumber=" + tableNumber + ", totalPrice="
                + totalPrice + ", menus=" + menus + "]";
    }
    
    public enum Status {
        RESERVED,
        FINISHED,
        OPEN
    }

    @Override
    public boolean isNew() {
        if(menus == null) {
            return false;
        }
        return true;
    }
}

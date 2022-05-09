package com.example.restaurantbillingsystem.domain;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Table;

@Table("order_item")
public class MenuRef implements Persistable<Long>{
    
    @Id
    private Long id;
    private Long menuId;
    
    public MenuRef() {
    }
    
    public MenuRef(Long menuId) {
        this.menuId = menuId;
    }

    public MenuRef(Long id, Long menuId) {
        this.id = id;
        this.menuId = menuId;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, menuId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MenuRef other = (MenuRef) obj;
        return Objects.equals(id, other.id) && Objects.equals(menuId, other.menuId);
    }

    @Override
    public String toString() {
        return "MenuRef [id=" + id + ", menuId=" + menuId + "]";
    }

    @Override
    public boolean isNew() {
        if(id != null) {
            return false;
        }
        return true;
    }
    
}

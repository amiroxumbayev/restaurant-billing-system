package com.example.restaurantbillingsystem.data;

import java.util.Optional;

import com.example.restaurantbillingsystem.Menu;

public interface MenuRepository {

    Iterable<Menu> findAll();
    
    Optional<Menu> findById(int id);
    
    Menu save(Menu menu);
    
    int update(int id, Menu menu);
    
    int delete(int id);
}

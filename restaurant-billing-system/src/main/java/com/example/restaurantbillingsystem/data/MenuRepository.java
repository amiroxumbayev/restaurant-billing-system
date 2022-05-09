package com.example.restaurantbillingsystem.data;

import org.springframework.data.repository.CrudRepository;

import com.example.restaurantbillingsystem.domain.Menu;

public interface MenuRepository extends CrudRepository<Menu, Long>{
    
}

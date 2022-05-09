package com.example.restaurantbillingsystem.data;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.restaurantbillingsystem.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
    Iterable<Order> findByPlacedAtLessThan(@Param("keyword") Date date);
}

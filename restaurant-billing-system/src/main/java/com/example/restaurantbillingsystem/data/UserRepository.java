package com.example.restaurantbillingsystem.data;

import org.springframework.data.repository.CrudRepository;

import com.example.restaurantbillingsystem.domain.User;

public interface UserRepository extends CrudRepository<User, Long> {
    
    User findByName(String name);
}

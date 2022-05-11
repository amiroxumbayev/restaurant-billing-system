package com.example.restaurantbillingsystem.data;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.restaurantbillingsystem.domain.Menu.Type;
import com.example.restaurantbillingsystem.domain.Order;

public interface OrderRepository extends CrudRepository<Order, Long>{
    
    Iterable<Order> findByPlacedAtLessThan(@Param("keyword") Date date);
    @Query("SELECT COUNT(order_item.id) _sum FROM public.order_item RIGHT JOIN public.menu on order_item.menu_id = menu.menu_id "
            + "GROUP BY menu.type "
            + "ORDER BY menu.type")
    List<Long> findCountItemsByTypes();
}

package com.example.restaurantbillingsystem.web;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurantbillingsystem.data.MenuRepository;
import com.example.restaurantbillingsystem.data.OrderRepository;
import com.example.restaurantbillingsystem.domain.Menu;
import com.example.restaurantbillingsystem.domain.MenuRef;
import com.example.restaurantbillingsystem.domain.Order;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderRepository orderRepo;
    private final MenuRepository menuRepo;

    @Autowired
    public OrdersController(OrderRepository orderRepo, MenuRepository menuRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
    }

    @GetMapping
    public String orders(Model model, Date date) {
        if (date != null) {
            Iterable<Order> list = orderRepo.findByPlacedAtLessThan(date);
            model.addAttribute("orders", list);
        } else {
            Iterable<Order> list = orderRepo.findAll();
            model.addAttribute("orders", list);
        }
        return "userPages/orders";
    }

    @GetMapping("/order-items/{id}")
    public String showOrderItems(Model m, @PathVariable("id") int id) {

        Order order = orderRepo.findById((long) id).orElse(new Order());
        List<Menu> menus = new ArrayList<>();
        for (MenuRef menuRef : order.getMenus()) {
            menus.add(menuRepo.findById(menuRef.getMenuId()).get());
        }
        m.addAttribute("items", menus);
        return "userPages/orderitems";
    }
}

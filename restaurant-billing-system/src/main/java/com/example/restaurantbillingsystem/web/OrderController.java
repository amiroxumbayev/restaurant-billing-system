package com.example.restaurantbillingsystem.web;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.example.restaurantbillingsystem.data.MenuRepository;
import com.example.restaurantbillingsystem.data.OrderRepository;
import com.example.restaurantbillingsystem.domain.Menu;
import com.example.restaurantbillingsystem.domain.MenuRef;
import com.example.restaurantbillingsystem.domain.Order;

@Controller
@RequestMapping("/order")
@SessionAttributes("order")
public class OrderController {
    
    private final OrderRepository orderRepo;
    private final MenuRepository menuRepo;

    @Autowired
    public OrderController(OrderRepository orderRepo, MenuRepository menuRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
    }
    
    @ModelAttribute(name = "order")
    public Order order() {
        Order order = new Order();
        order.setId(orderRepo.count());
        return order;
    }
    
    @ModelAttribute(name = "item")
    public Menu item() {
        return new Menu();
    }

    @GetMapping
    public String order(Model m, @ModelAttribute Order order) {
        Iterable<Menu> list = menuRepo.findAll();
        Menu.Type[] types = Menu.Type.values();
        for (Menu.Type type : types) {
            m.addAttribute(type.toString().toLowerCase(), filterByType(list, type));
        }
        List<Menu> menus = new ArrayList<>();
        for(MenuRef menuRef:order.getMenus()) {
            menus.add(menuRepo.findById(menuRef.getMenuId()).get());
        }
        m.addAttribute("basket", menus);
        return "userPages/addorder";
    }

    private Iterable<Menu> filterByType(Iterable<Menu> ingredients, Menu.Type type) {
        return StreamSupport.stream(ingredients.spliterator(), false).filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String showOrderItems(@Valid Order order, Errors errors, SessionStatus sessionStatus) {

            if (errors.hasErrors()) {
                return "userPages/addorder";
            }
            
            double totalPrice = 0.;
            for(MenuRef menuRef:order.getMenus()) {
                totalPrice+=menuRepo.findById(menuRef.getMenuId()).get().getPrice();
            }
            order.setTotalPrice(totalPrice);
            order.setId(null);
            orderRepo.save(order);
            sessionStatus.setComplete();
            return "redirect:/orders";
    }
    
    @PostMapping("/add-item/{id}")
    public String showOrderItems(@PathVariable("id") int id, @ModelAttribute Order order) {
        
        Menu menu = menuRepo.findById((long) id).orElseThrow(()->new IllegalArgumentException("Item not found"));
        order.addMenu(menu);
        return "redirect:/order";
    }
}

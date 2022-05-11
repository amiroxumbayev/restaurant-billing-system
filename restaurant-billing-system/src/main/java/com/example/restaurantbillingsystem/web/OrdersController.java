package com.example.restaurantbillingsystem.web;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurantbillingsystem.data.MenuRepository;
import com.example.restaurantbillingsystem.data.OrderRepository;
import com.example.restaurantbillingsystem.data.chart.AnnualReportRepository;
import com.example.restaurantbillingsystem.domain.Menu;
import com.example.restaurantbillingsystem.domain.Menu.Type;
import com.example.restaurantbillingsystem.domain.MenuRef;
import com.example.restaurantbillingsystem.domain.Order;
import com.example.restaurantbillingsystem.domain.chart.AnnualReport;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    private final OrderRepository orderRepo;
    private final MenuRepository menuRepo;
    private final AnnualReportRepository annualReportRepo;

    @Autowired
    public OrdersController(OrderRepository orderRepo, MenuRepository menuRepo, AnnualReportRepository annualReportRepo) {
        this.orderRepo = orderRepo;
        this.menuRepo = menuRepo;
        this.annualReportRepo = annualReportRepo;
    }

    @GetMapping
    public String orders(Model model, Date date) {
        Iterable<AnnualReport> reports = annualReportRepo.findAll();
        Type[] types = Type.values();
        Map<Type, Long> map = new HashMap<>();
        for (Type type : types) {
            map.put(type, 0L);
        }
        Iterable<Menu> menus= menuRepo.findAll();
        Iterable<Order> list;
        if (date != null) {
            list = orderRepo.findByPlacedAtLessThan(date);
        } else {
            list = orderRepo.findAll();
        }
        list.forEach(e -> e.getMenus().forEach(orderMenu -> menus.forEach(menu -> {
            if(orderMenu.getMenuId().equals(menu.getMenuId())) {
                map.put(menu.getType(), (map.get(menu.getType()) +1));
            }
        })));
        model.addAttribute("map", map);
        model.addAttribute("orders", list);
        model.addAttribute("annualreport", reports);
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

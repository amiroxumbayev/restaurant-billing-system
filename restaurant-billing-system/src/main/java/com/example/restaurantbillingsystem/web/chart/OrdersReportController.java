package com.example.restaurantbillingsystem.web.chart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.restaurantbillingsystem.data.chart.IncomeReportRepository;
import com.example.restaurantbillingsystem.data.chart.OrdersReportRepository;
import com.example.restaurantbillingsystem.domain.chart.IncomeReport;
import com.example.restaurantbillingsystem.domain.chart.OrdersReport;

@Controller
@RequestMapping("/statistics")
public class OrdersReportController {
    
    private final OrdersReportRepository ordersReportRepository;
    private final IncomeReportRepository incomeReportRepository;
    
    @Autowired
    public OrdersReportController(OrdersReportRepository ordersReportRepository, IncomeReportRepository incomeReportRepository) {
        this.ordersReportRepository = ordersReportRepository;
        this.incomeReportRepository = incomeReportRepository;
    }

    @GetMapping
    public String orders(Model model) {
        Iterable<OrdersReport> reports = ordersReportRepository.findAll();
        model.addAttribute("ordersreport", reports);
        Iterable<IncomeReport> incomeReport = incomeReportRepository.findAll();
        model.addAttribute("incomereport", incomeReport);
        return "userPages/statistics";
    }
}

package com.example.restaurantbillingsystem.domain.chart;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class OrdersReport {
    
    @Id
    private final Integer year;
    private final Long count;

    public OrdersReport(Integer year, Long count) {
        this.year = year;
        this.count = count;
    }

    public Integer getYear() {
        return year;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, year);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        OrdersReport other = (OrdersReport) obj;
        return Objects.equals(count, other.count) &&
                Objects.equals(year, other.year);
    }
}

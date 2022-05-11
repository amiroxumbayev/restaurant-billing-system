package com.example.restaurantbillingsystem.domain.chart;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class IncomeReport {
    
    @Id
    private final Integer month;
    private final Double max;
    private final Double min;
    private final Double avg;
    
    public IncomeReport(Integer month, Double max, Double min, Double avg) {
        this.month = month;
        this.max = max;
        this.min = min;
        this.avg = avg;
    }

    public Integer getMonth() {
        return month;
    }

    public Double getMax() {
        return max;
    }

    public Double getMin() {
        return min;
    }

    public Double getAvg() {
        return avg;
    }

    @Override
    public int hashCode() {
        return Objects.hash(avg, max, min, month);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        IncomeReport other = (IncomeReport) obj;
        return Objects.equals(avg, other.avg) &&
                Objects.equals(max, other.max) &&
                Objects.equals(min, other.min) &&
                Objects.equals(month, other.month);
    }

    @Override
    public String toString() {
        return "IncomeReport [month=" + month + ", max=" + max + ", min=" + min + ", avg=" + avg + "]";
    }
    
}

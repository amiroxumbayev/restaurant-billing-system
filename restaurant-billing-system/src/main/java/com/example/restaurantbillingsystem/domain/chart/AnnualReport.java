package com.example.restaurantbillingsystem.domain.chart;

import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table
public class AnnualReport {

    @Id
    private final Integer month;
    private final Long count;

    public AnnualReport(Integer month, Long count) {
        this.month = month;
        this.count = count;
    }

    public Integer getMonth() {
        return month;
    }

    public Long getCount() {
        return count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, month);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AnnualReport other = (AnnualReport) obj;
        return Objects.equals(count, other.count) &&
                Objects.equals(month, other.month);
    }

    @Override
    public String toString() {
        return "AnnualReport [month=" + month + ", count=" + count + "]";
    }

}

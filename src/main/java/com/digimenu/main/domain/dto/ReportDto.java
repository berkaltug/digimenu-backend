package com.digimenu.main.domain.dto;

import com.digimenu.main.domain.projection.ReportProjection;

import java.math.BigDecimal;
import java.util.Objects;

public class ReportDto implements ReportProjection {

    private Integer count;
    private String name;
    private BigDecimal totalPrice;

    public ReportDto(Integer count, String name, BigDecimal totalPrice) {
        this.count = count;
        this.name = name;
        this.totalPrice = totalPrice;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportDto reportDto = (ReportDto) o;
        return Objects.equals(count, reportDto.count) &&
                Objects.equals(name, reportDto.name) &&
                Objects.equals(totalPrice, reportDto.totalPrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, name, totalPrice);
    }

    @Override
    public String toString() {
        return "ReportDto{" +
                "count=" + count +
                ", name='" + name + '\'' +
                ", totalPrice=" + totalPrice +
                '}';
    }
}

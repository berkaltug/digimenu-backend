package com.digimenu.main.domain.dto;

import java.math.BigDecimal;
import java.util.Objects;

public class PastOrderDto {

    private Integer count;
    private BigDecimal total;
    private String name;

    public PastOrderDto(Integer count, BigDecimal total, String name) {
        this.count = count;
        this.total = total;
        this.name = name;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PastOrderDto that = (PastOrderDto) o;
        return Objects.equals(count, that.count) &&
                Objects.equals(total, that.total) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, total, name);
    }

    @Override
    public String toString() {
        return "PastOrderDto{" +
                "count=" + count +
                ", total=" + total +
                ", name='" + name + '\'' +
                '}';
    }
}

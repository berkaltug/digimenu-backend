package com.digimenu.main.domain.dto;

import com.digimenu.main.domain.entity.Category;

public class CatSortDto {
    private  Long id;
    private Category category;
    private Integer orderNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Integer getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(Integer orderNo) {
        this.orderNo = orderNo;
    }
}

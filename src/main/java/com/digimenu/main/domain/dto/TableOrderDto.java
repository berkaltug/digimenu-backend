package com.digimenu.main.domain.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableOrderDto {
    List<MenuDto> items=new ArrayList();
    Long resId;
    Integer masaNo;

    public List<MenuDto> getItems() {
        return items;
    }

    public void setItems(List<MenuDto> items) {
        this.items = items;
    }

    public Long getResId() {
        return resId;
    }

    public void setResId(Long resId) {
        this.resId = resId;
    }

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableOrderDto that = (TableOrderDto) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(resId, that.resId) &&
                Objects.equals(masaNo, that.masaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, resId, masaNo);
    }

    @Override
    public String toString() {
        return "TableOrderDto{" +
                "items=" + items +
                ", resId=" + resId +
                ", masaNo=" + masaNo +
                '}';
    }
}

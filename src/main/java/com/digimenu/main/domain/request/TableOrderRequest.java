package com.digimenu.main.domain.request;

import com.digimenu.main.domain.dto.MenuDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TableOrderRequest {
    List<MenuDto> items=new ArrayList<>();
    List<MenuDto> kampanya=new ArrayList<>();

    public List<MenuDto> getItems() {
        return items;
    }

    public void setItems(List<MenuDto> items) {
        this.items = items;
    }

    public List<MenuDto> getKampanya() {
        return kampanya;
    }

    public void setKampanya(List<MenuDto> kampanya) {
        this.kampanya = kampanya;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableOrderRequest that = (TableOrderRequest) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "TableOrderRequest{" +
                "items=" + items +
                '}';
    }
}

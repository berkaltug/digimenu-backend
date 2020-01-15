package com.digimenu.main.domain.response;

import com.digimenu.main.domain.entity.Menu;

import java.util.List;
import java.util.Objects;

public class GetMenuResponse {
    List<Menu> items;

    public List<Menu> getItems() {
        return items;
    }

    public void setItems(List<Menu> items) {
        this.items = items;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetMenuResponse that = (GetMenuResponse) o;
        return Objects.equals(items, that.items);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items);
    }

    @Override
    public String toString() {
        return "GetMenuResponse{" +
                "items=" + items +
                '}';
    }
}

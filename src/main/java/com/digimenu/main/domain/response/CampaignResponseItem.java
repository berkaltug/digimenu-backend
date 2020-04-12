package com.digimenu.main.domain.response;

import java.math.BigDecimal;
import java.util.Objects;

public class CampaignResponseItem {

    public Long id;
    public String name;
    public String contents;
    public BigDecimal price;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignResponseItem that = (CampaignResponseItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(contents, that.contents) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents, price);
    }

    @Override
    public String toString() {
        return "CampaignResponseItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents='" + contents + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}

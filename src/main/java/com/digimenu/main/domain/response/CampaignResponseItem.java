package com.digimenu.main.domain.response;

import java.math.BigDecimal;
import java.util.Objects;

public class CampaignResponseItem {

    private Long id;
    private String name;
    private String contents;
    private BigDecimal price;
    private Float rating;

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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CampaignResponseItem that = (CampaignResponseItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(contents, that.contents) &&
                Objects.equals(price, that.price) &&
                Objects.equals(rating, that.rating);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, contents, price, rating);
    }

    @Override
    public String toString() {
        return "CampaignResponseItem{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", contents='" + contents + '\'' +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}

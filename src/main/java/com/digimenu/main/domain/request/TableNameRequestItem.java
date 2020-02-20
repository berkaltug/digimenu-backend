package com.digimenu.main.domain.request;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class TableNameRequestItem {

    private Long id;
    @NotEmpty
    private Integer masaNo;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
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
        TableNameRequestItem that = (TableNameRequestItem) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(masaNo, that.masaNo) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masaNo, name);
    }

    @Override
    public String toString() {
        return "TableNameRequestItem{" +
                "id=" + id +
                ", masaNo=" + masaNo +
                ", name='" + name + '\'' +
                '}';
    }
}

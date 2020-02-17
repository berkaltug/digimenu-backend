package com.digimenu.main.domain.request;

import javax.validation.constraints.NotEmpty;
import java.util.Objects;

public class TableNameRequestItem {
    @NotEmpty
    private Integer masaNo;
    @NotEmpty
    private String name;

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
        return Objects.equals(masaNo, that.masaNo) &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masaNo, name);
    }

    @Override
    public String toString() {
        return "TableNameRequestItem{" +
                "masaNo=" + masaNo +
                ", name='" + name + '\'' +
                '}';
    }
}

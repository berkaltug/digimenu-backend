package com.digimenu.main.domain.request;

import java.util.List;
import java.util.Objects;

public class TableNameRequest {

    private List<TableNameRequestItem> requestItemList;

    public List<TableNameRequestItem> getRequestItemList() {
        return requestItemList;
    }

    public void setRequestItemList(List<TableNameRequestItem> requestItemList) {
        this.requestItemList = requestItemList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TableNameRequest that = (TableNameRequest) o;
        return Objects.equals(requestItemList, that.requestItemList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestItemList);
    }

    @Override
    public String toString() {
        return "TableNameRequest{" +
                "requestItemList=" + requestItemList +
                '}';
    }
}

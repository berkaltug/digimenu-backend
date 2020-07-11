package com.digimenu.main.domain.util;

import com.digimenu.main.domain.entity.CategorySort;
import com.digimenu.main.domain.response.MenuResponseItem;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class CategoryComparator implements Comparator<MenuResponseItem> {

    private List<String> sortList;

    public CategoryComparator(List<String> sortList) {
        this.sortList = sortList;
    }

    @Override
    public int compare(MenuResponseItem menuResponseItem, MenuResponseItem t1) {
        Integer int1=sortList.indexOf(menuResponseItem.getCategory());
        Integer int2= sortList.indexOf(t1.getCategory());
        return Integer.compare(int1,int2);
    }
}

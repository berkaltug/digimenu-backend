package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.entity.CategorySort;
import com.digimenu.main.domain.request.CatSortRequest;

import java.util.ArrayList;
import java.util.List;

public class CartSortEntityConverter {
    public static List<CategorySort> convert(CatSortRequest request){
        ArrayList<CategorySort> list = new ArrayList<CategorySort>();
        request.getDtoList().forEach(dto -> {
            CategorySort entity=new CategorySort();
            entity.setCategory(dto.getCategory());
            entity.setSortingNo(dto.getOrderNo());
            entity.setId(dto.getId());
            list.add(entity);
        });
        return list;
    }
}

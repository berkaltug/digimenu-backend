package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.CatSortDto;
import com.digimenu.main.domain.entity.Category;
import com.digimenu.main.domain.entity.CategorySort;
import com.digimenu.main.domain.request.CatSortRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CatSortRequestConverter {
    public  static CatSortRequest convert(List<CategorySort> entities){
        ArrayList<CatSortDto> dtoList = new ArrayList<CatSortDto>();
        entities.forEach(e->{
            CatSortDto dto=new CatSortDto();
            dto.setCategory(e.getCategory());
            dto.setId(e.getId());
            dto.setOrderNo(e.getSortingNo());
            dtoList.add(dto);
        });
        CatSortRequest request= new CatSortRequest();
        request.setDtoList(dtoList);
        return request;
    }
}

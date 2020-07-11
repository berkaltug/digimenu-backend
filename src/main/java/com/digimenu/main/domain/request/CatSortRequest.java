package com.digimenu.main.domain.request;

import com.digimenu.main.domain.dto.CatSortDto;

import java.util.List;

public class CatSortRequest {
    private List<CatSortDto> dtoList;

    public List<CatSortDto> getDtoList() {
        return dtoList;
    }

    public void setDtoList(List<CatSortDto> dtoList) {
        this.dtoList = dtoList;
    }
}

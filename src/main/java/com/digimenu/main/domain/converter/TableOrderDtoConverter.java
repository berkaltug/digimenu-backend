package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.MenuDto;
import com.digimenu.main.domain.dto.TableOrderDto;
import com.digimenu.main.domain.request.TableOrderRequest;

import java.util.List;
import java.util.stream.Collectors;

public class TableOrderDtoConverter {

    public static TableOrderDto convert(TableOrderRequest request,Long resId,Integer masaNo){
        final TableOrderDto dto = new TableOrderDto();
        dto.setItems(request.getItems());
        dto.setMasaNo(masaNo);
        dto.setResId(resId);
        dto.setLatitude(request.getLatitude());
        dto.setLongitude(request.getLongitude());
        return dto;
    }
}

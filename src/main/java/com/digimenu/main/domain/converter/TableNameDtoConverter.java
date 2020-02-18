package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.request.TableNameRequest;
import com.digimenu.main.domain.request.TableNameRequestItem;

import java.util.ArrayList;
import java.util.List;

public class TableNameDtoConverter {
    public static List<TableNameDto> convert(TableNameRequest request, Restaurant restaurant){
        final List<TableNameDto> list=new ArrayList<>();
        request.getRequestItemList().forEach(item->{
            TableNameDto dto=new TableNameDto();
            dto.setMasaNo(item.getMasaNo());
            dto.setName(item.getName());
            dto.setRestaurant(restaurant);
            list.add(dto);
        });
        return list;
    }
}

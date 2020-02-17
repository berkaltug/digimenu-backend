package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.request.TableNameRequestItem;

public class TableNameDtoConverter {
    public static TableNameDto convert(TableNameRequestItem item, Restaurant restaurant){
        final TableNameDto dto=new TableNameDto();
        dto.setMasaNo(item.getMasaNo());
        dto.setName(item.getName());
        dto.setRestaurant(restaurant);
        return dto;
    }
}

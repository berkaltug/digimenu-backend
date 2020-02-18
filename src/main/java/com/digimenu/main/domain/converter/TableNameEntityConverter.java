package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.TableName;

import java.util.ArrayList;
import java.util.List;

public class TableNameEntityConverter {
    public static List<TableName> convert(List<TableNameDto> dtoList){
        List<TableName> list=new ArrayList<>();
        dtoList.forEach(item->{
            TableName tableName = new TableName();
            tableName.setName(item.getName());
            tableName.setRestaurant(item.getRestaurant());
            tableName.setMasaNo(item.getMasaNo());
            list.add(tableName);
        });
        return list;
    }
}

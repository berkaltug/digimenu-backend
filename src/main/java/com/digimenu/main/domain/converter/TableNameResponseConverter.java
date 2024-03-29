package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.entity.TableName;
import com.digimenu.main.domain.request.TableNameRequestItem;
import com.digimenu.main.domain.response.TableNameResponse;

import java.util.ArrayList;
import java.util.List;

public class TableNameResponseConverter {
    public static List<TableNameResponse> convert(List<TableName> tableNameList){
        final List<TableNameResponse> list = new ArrayList<TableNameResponse>();
        tableNameList.forEach(item->{
            TableNameResponse response=new TableNameResponse();
            response.setMasaNo(item.getMasaNo());
            response.setName(item.getName());
            list.add(response);
        });
        return list;
    }

    public static List<TableNameRequestItem> convertToRequest(List<TableName> tableNameList){
        final List<TableNameRequestItem> list = new ArrayList<>();
        tableNameList.forEach(item->{
            TableNameRequestItem requestItem=new TableNameRequestItem();
            requestItem.setMasaNo(item.getMasaNo());
            requestItem.setName(item.getName());
            requestItem.setId(item.getId());
            list.add(requestItem);
        });
        return list;
    }
}

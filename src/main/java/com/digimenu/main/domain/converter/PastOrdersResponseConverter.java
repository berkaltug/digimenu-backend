package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.PastOrderDto;
import com.digimenu.main.domain.dto.PastOrdersResponseDto;
import com.digimenu.main.domain.projection.PastOrdersProjection;
import com.digimenu.main.domain.response.PastOrdersResponse;
import com.digimenu.main.domain.util.PastTuple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PastOrdersResponseConverter {
    public static PastOrdersResponse convert(Map<PastTuple, List<PastOrdersProjection>> orders) {
        PastOrdersResponse response = new PastOrdersResponse();
        Map<PastTuple, List<PastOrderDto>> newmap = new HashMap<>();
        orders.forEach((k, v) -> {
                    List<PastOrderDto> list = new ArrayList<>();
                    v.forEach(projection -> {
                        PastOrderDto dto = new PastOrderDto(projection.getCount(), projection.getTotal(), projection.getName());
                        list.add(dto);
                    });
                    newmap.put(k, list);
                }
        );
        List<PastOrdersResponseDto> dtoList=new ArrayList<>();
        newmap.forEach((k,v)->{
            PastOrdersResponseDto dto = new PastOrdersResponseDto(k.getRestaurantId(), k.getRestaurantName(), k.getOrderDate(), v);
            dtoList.add(dto);
        });
        response.setPastOrders(dtoList);
        return response;
    }
}

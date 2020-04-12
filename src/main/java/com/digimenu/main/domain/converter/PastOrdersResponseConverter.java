package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.PastOrderDto;
import com.digimenu.main.domain.dto.PastOrdersResponseDto;
import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.domain.projection.PastOrdersProjection;
import com.digimenu.main.domain.response.PastOrdersResponse;
import com.digimenu.main.domain.util.PastTuple;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

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

    public static PastOrdersResponse convert(List<Siparis> siparisList){
        PastOrdersResponse response=new PastOrdersResponse();
        List<PastOrdersResponseDto> pastOrdersResponseDtoList=new ArrayList<>();
        //siparis list içinde tableOrdersa git , bunları (itemname,List) şeklinde maple ,sonra bu mapten pastOrders dtoları oluştur,
        //yapabiliyosan Map<PastTuple, List<PastOrderDto>> oluşturmayı dene
        siparisList.forEach(siparis -> {
            List<PastOrderDto> pastOrderDtoList=new ArrayList<>();
            Map<String, List<Table_Orders>> map = siparis.getTableOrders().stream().collect(Collectors.groupingBy(Table_Orders::getItem));
            map.forEach((k,v)->{
                BigDecimal total=v.get(0).getPrice().multiply(new BigDecimal(v.size()));
                PastOrderDto dto=new PastOrderDto(v.size(),total,k);
                pastOrderDtoList.add(dto);
            });
            PastOrdersResponseDto pastOrdersResponseDto=new PastOrdersResponseDto(
                    siparis.getRestaurant().getId(),
                    siparis.getRestaurant().getName(),
                    new Date(siparis.getSiparisTarihi().getTime()),
                    pastOrderDtoList);
            pastOrdersResponseDtoList.add(pastOrdersResponseDto);
        });
        response.setPastOrders(pastOrdersResponseDtoList);
        return response;
    }
}

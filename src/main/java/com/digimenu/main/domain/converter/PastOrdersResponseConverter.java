package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.PastOrderDto;
import com.digimenu.main.domain.dto.PastOrdersResponseDto;
import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.domain.projection.PastOrdersProjection;
import com.digimenu.main.domain.response.PastOrdersResponse;
import com.digimenu.main.domain.util.PastTuple;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

public class PastOrdersResponseConverter {

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
                    siparis.getOrderId(),
                    siparis.getRestaurant().getId(),
                    siparis.getRestaurant().getName(),
                    new SimpleDateFormat("dd/MM/yyyy hh:mm").format(new Date(siparis.getSiparisTarihi().getTime())),
                    siparis.getVoted(),
                    pastOrderDtoList);
            pastOrdersResponseDtoList.add(pastOrdersResponseDto);
        });
        response.setPastOrders(pastOrdersResponseDtoList);
        return response;
    }
}

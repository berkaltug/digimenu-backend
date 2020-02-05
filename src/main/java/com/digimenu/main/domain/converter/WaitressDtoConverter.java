package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.CallWaitressDto;
import com.digimenu.main.domain.request.LocationRequest;

public class WaitressDtoConverter {

    public static CallWaitressDto convert(LocationRequest request,Long restaurantId,Integer masaNo){
        CallWaitressDto dto = new CallWaitressDto();
        dto.setLatitude(request.getLatitude());
        dto.setLongitude(request.getLongitude());
        dto.setMasaNo(masaNo);
        dto.setRestaurantId(restaurantId);
        return  dto;
    }
}

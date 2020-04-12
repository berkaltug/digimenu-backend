package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.MenuDto;
import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.entity.Table_Orders;
import com.digimenu.main.security.User;

public class TableOrdersEntityConverter {
    public static Table_Orders convert(MenuDto dto, Integer masaNo, Restaurant res, User user, Siparis siparis){
        final Table_Orders entity = new Table_Orders();
        entity.setItem(dto.getItem());
        entity.setMasa(masaNo);
        entity.setPrice(dto.getPrice());
        entity.setRestaurant(res);
        entity.setUser(user);
        entity.setSiparis(siparis);
        return entity;
    }
}

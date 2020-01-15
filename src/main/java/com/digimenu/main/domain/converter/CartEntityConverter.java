package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.MenuDto;
import com.digimenu.main.domain.entity.Cart;
import com.digimenu.main.domain.entity.Restaurant;

public class CartEntityConverter {
    public static Cart convert(MenuDto dto, Integer masaNo, Long resId) {
        final Cart cart = new Cart();
        cart.setItem(dto.getItem());
        cart.setMasaNo(masaNo);
        cart.setPrice(dto.getPrice());
        cart.setRestaurantId(resId);
        cart.setMessage(dto.getMessage());
        return cart;
    }
}

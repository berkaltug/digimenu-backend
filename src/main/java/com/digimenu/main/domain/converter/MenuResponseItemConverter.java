package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.response.MenuResponseItem;

public class MenuResponseItemConverter {

    public static MenuResponseItem convert(Menu menu){
        MenuResponseItem response = new MenuResponseItem();
        response.setId(menu.getId());
        response.setCategory(menu.getCategory());
        response.setIngredients(menu.getIngredients());
        response.setItem(menu.getItem());
        response.setPrice(menu.getPrice());
        response.setRating(menu.getRating());
        return response;
    }
}

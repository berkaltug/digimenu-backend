package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.MenuDto;
import com.digimenu.main.domain.entity.Menu;

public class MenuDtoConverter {
    public static MenuDto convert(Menu menu){
        MenuDto dto = new MenuDto();
        dto.setId(menu.getId());
        dto.setCategory(menu.getCategory());
        dto.setIngredients(menu.getIngredients());
        dto.setItem(menu.getItem());
        dto.setPrice(menu.getPrice());
        return dto;
    }
}

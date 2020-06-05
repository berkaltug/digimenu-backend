package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.PanelMenuDto;
import com.digimenu.main.domain.entity.Menu;

public class PanelMenuDtoConverter {
    public static Menu convert(PanelMenuDto panelMenuDto){
        Menu menu=new Menu();
        menu.setId(panelMenuDto.getId());
        menu.setItem(panelMenuDto.getItem());
        menu.setIngredients(panelMenuDto.getIngredients());
        menu.setRating(panelMenuDto.getRating());
        menu.setActive(panelMenuDto.getActive());
        menu.setFavourite(panelMenuDto.getFavourite());
        menu.setPrice(panelMenuDto.getPrice());
        menu.setCategory(panelMenuDto.getCategory());
        return menu;
    }
}

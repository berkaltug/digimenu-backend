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
        menu.setVoteCount(panelMenuDto.getVoteCount());
        return menu;
    }

    public static PanelMenuDto convert(Menu menu){
        PanelMenuDto dto = new PanelMenuDto();
        dto.setId(menu.getId());
        dto.setActive(menu.getActive());
        dto.setCategory(menu.getCategory());
        dto.setFavourite(menu.getFavourite());
        dto.setIngredients(menu.getIngredients());
        dto.setItem(menu.getItem());
        dto.setPrice(menu.getPrice());
        dto.setRating(menu.getRating());
        dto.setRestaurantId(menu.getRestaurant().getId());
        dto.setVoteCount(menu.getVoteCount());
        return dto;
    }
}

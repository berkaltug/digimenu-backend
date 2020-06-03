package com.digimenu.main.controller;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.response.GetMenuResponse;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/webmenu")
public class WebMenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping(path="/{id}")
    public String getWebMenu(@PathVariable("id") Long id, Model model){
        GetMenuResponse response = menuService.getMenuItemsByRestaurant(id);
        model.addAttribute("favourites",response.getFavourites());
        model.addAttribute("campaigns",response.getCampaigns());
        model.addAttribute("itemsMap",menuService.orderItemsByCategory(response.getItems()));
        model.addAttribute("restaurantName",restaurantService.getRestaurant(id).getName());
        return "webmenu";
    }
}

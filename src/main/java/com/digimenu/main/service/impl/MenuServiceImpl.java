package com.digimenu.main.service.impl;

import java.text.Collator;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import com.digimenu.main.domain.converter.MenuDtoConverter;
import com.digimenu.main.domain.response.GetMenuResponse;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.MenuRepository;
@Service
public class MenuServiceImpl implements MenuService {
	
	private MenuRepository mr;
	private RestaurantService rs;

	@Autowired
	public MenuServiceImpl(MenuRepository mr, RestaurantService rs) {
		this.mr = mr;
		this.rs = rs;
	}

	@Override
	public GetMenuResponse getMenuItemsByRestaurant(Long id) {
		GetMenuResponse response = new GetMenuResponse();
		List<Menu> itemList = mr.getByRestaurant(id);
		response.setItems(itemList
				.stream()
				.filter(item -> item.getActive())
				.collect(Collectors.toList()).stream().map(item-> MenuDtoConverter.convert(item)).collect(Collectors.toList()));
		response.setFavourites(itemList
				.stream()
				.filter(item->item.getFavourite())
				.collect(Collectors.toList()).stream().map(item-> MenuDtoConverter.convert(item)).collect(Collectors.toList()));
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		response.getItems().sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		response.getFavourites().sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return response;
	}

	@Override
	public List<Menu> getAllItemsByRestaurant(Long id) {
		List<Menu> items = mr.getByRestaurant(id);
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		items.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return items;
	}

	@Override
	public Menu getMenuItem(Long id) {
		return mr.getOne(id);
	}

	@Override
	public void saveMenuItem(Menu menu) {
		mr.save(menu);
	}

	@Override
	public void deleteMenuItem(Menu menu) {
		mr.delete(menu);
	}
	
	@Override
	@Transactional
	@Modifying
	public void updateMenuItem(Menu menu) {
		mr.save(menu);
	}

	@Override
	public List<Menu> getPassiveItemsByRestaurant(Long id) {

		List<Menu> passives = mr.getByRestaurant(id)
				.stream()
				.filter(item -> !item.getActive())
				.collect(Collectors.toList());


		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		passives.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return passives;
	}

	@Override
	public List<Menu> getFavoriteItemsByRestaurant(Long id){
		List<Menu> favourite = mr.getByRestaurant(id)
				.stream()
				.filter(item -> item.getFavourite())
				.collect(Collectors.toList());
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		favourite.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return favourite;
	}

}

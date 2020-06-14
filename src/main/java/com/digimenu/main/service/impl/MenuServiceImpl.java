package com.digimenu.main.service.impl;

import java.text.Collator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.digimenu.main.domain.converter.CampaignResponseItemConverter;
import com.digimenu.main.domain.converter.MenuDtoConverter;
import com.digimenu.main.domain.converter.MenuResponseItemConverter;
import com.digimenu.main.domain.converter.PanelMenuDtoConverter;
import com.digimenu.main.domain.dto.PanelMenuDto;
import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.response.GetMenuResponse;
import com.digimenu.main.domain.response.MenuResponseItem;
import com.digimenu.main.service.CampaignService;
import com.digimenu.main.service.CloudinaryService;
import com.digimenu.main.service.MenuService;
import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.digimenu.main.domain.entity.Menu;
import com.digimenu.main.repository.MenuRepository;
@Service
public class MenuServiceImpl implements MenuService {
	
	private MenuRepository menuRepository;
	private RestaurantService restaurantService;
	private CampaignService campaignService;
	private CloudinaryService cloudinaryService;

	@Autowired
	public MenuServiceImpl(MenuRepository menuRepository, RestaurantService restaurantService, CampaignService campaignService, CloudinaryService cloudinaryService) {
		this.menuRepository = menuRepository;
		this.restaurantService = restaurantService;
		this.campaignService = campaignService;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public GetMenuResponse getMenuItemsByRestaurant(Long id) {
		GetMenuResponse response = new GetMenuResponse();
		List<Menu> itemList = menuRepository.getByRestaurant(id);
		List<Campaign> campaigns = campaignService.getAllCampaignsByRestaurant(restaurantService.getRestaurant(id));
		response.setItems(itemList
				.stream()
				.filter(item -> item.getActive())
				.collect(Collectors.toList()).stream().map(item-> MenuResponseItemConverter.convert(item)).collect(Collectors.toList()));
		response.setFavourites(itemList
				.stream()
				.filter(item->item.getFavourite() && item.getActive())
				.collect(Collectors.toList()).stream().map(item-> MenuResponseItemConverter.convert(item)).collect(Collectors.toList()));
		response.setCampaigns(campaigns
				.stream()
				.map(item-> CampaignResponseItemConverter.convert(item))
				.collect(Collectors.toList()));
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		response.getItems().sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		response.getFavourites().sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		response.getCampaigns().sort((campaign,t1) -> collator.compare(campaign.getName(),t1.getName()));
		return response;
	}

	@Override
	public List<Menu> getAllItemsByRestaurant(Long id) {
		List<Menu> items = menuRepository.getByRestaurant(id);
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		items.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return items;
	}

	@Override
	public Menu getMenuItem(Long id) {
		return menuRepository.getOne(id);
	}

	@Override
	public void saveMenuItem(PanelMenuDto panelMenuDto) {
		Menu menu = PanelMenuDtoConverter.convert(panelMenuDto);
		menu.setRestaurant(restaurantService.getLoggedInRestaurant());
		if(panelMenuDto.getImage()!=null && !panelMenuDto.getImage().isEmpty()){
			menu.setImagePublicId(cloudinaryService.uploadFile(panelMenuDto.getImage()));
		}
		menu.setRating(0f);
		menu.setVoteCount(0);
		menuRepository.save(menu);
	}

	@Override
	public void deleteMenuItem(Menu menu) {
		menuRepository.delete(menu);
	}
	
	@Override
	@Transactional
	@Modifying
	public void updateMenuItem(PanelMenuDto panelMenuDto) {
		Menu menu = PanelMenuDtoConverter.convert(panelMenuDto);
		menu.setRestaurant(restaurantService.getRestaurant(panelMenuDto.getRestaurantId()));
		menu.setVoteCount(panelMenuDto.getVoteCount());
		String oldImageId=getMenuItem(panelMenuDto.getId()).getImagePublicId();
		String newImageId;
		if(panelMenuDto.getImage()!=null && !panelMenuDto.getImage().isEmpty() && panelMenuDto.getShouldDelImage()==false){
			newImageId = cloudinaryService.updateFile(panelMenuDto.getImage(), oldImageId);
			menu.setImagePublicId(newImageId);
		}else if(panelMenuDto.getShouldDelImage()==true){
			cloudinaryService.deleteFile(oldImageId);
		}
		else{
			menu.setImagePublicId(oldImageId);
		}
		menuRepository.save(menu);
	}

	@Override
	@Transactional
	@Modifying
	public void updateMenuItem(Menu menu){
		menuRepository.save(menu);
	}

	@Override
	public List<Menu> getPassiveItemsByRestaurant(Long id) {

		List<Menu> passives = menuRepository.getByRestaurant(id)
				.stream()
				.filter(item -> !item.getActive())
				.collect(Collectors.toList());


		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		passives.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return passives;
	}

	@Override
	public List<Menu> getFavoriteItemsByRestaurant(Long id){
		List<Menu> favourite = menuRepository.getByRestaurant(id)
				.stream()
				.filter(item -> item.getFavourite())
				.collect(Collectors.toList());
		Collator collator= Collator.getInstance(new Locale("tr","TR"));
		favourite.sort((menu, t1) -> collator.compare(menu.getItem(),t1.getItem()));
		return favourite;
	}

	@Override
	public Optional<Menu> getMenuByRestaurantAndName(Restaurant restaurant, String name){
		return menuRepository.findByRestaurantAndItem(restaurant,name);
	}

	@Override
	public Map<String,List<MenuResponseItem>> orderItemsByCategory(List<MenuResponseItem> items){
		return items.stream().collect(Collectors.groupingBy(MenuResponseItem::getCategory));
	}
}

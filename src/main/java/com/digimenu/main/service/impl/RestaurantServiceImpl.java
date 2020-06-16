package com.digimenu.main.service.impl;

import com.digimenu.main.domain.converter.TableNameEntityConverter;
import com.digimenu.main.domain.converter.TableNameResponseConverter;
import com.digimenu.main.domain.dto.LogoDto;
import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.TableName;
import com.digimenu.main.domain.request.TableNameRequest;
import com.digimenu.main.domain.response.TableNameResponse;
import com.digimenu.main.repository.TableNameRepository;
import com.digimenu.main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.RestaurantRepository;
import com.digimenu.main.security.User;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantRepository rr;
	private SecurityService securityService;
	private UserService userService;
	private TableNameRepository tableNameRepository;
	private CloudinaryService cloudinaryService;

	@Autowired
	public RestaurantServiceImpl(RestaurantRepository rr, SecurityService securityService, UserService userService, TableNameRepository tableNameRepository, CloudinaryService cloudinaryService) {
		this.rr = rr;
		this.securityService = securityService;
		this.userService = userService;
		this.tableNameRepository = tableNameRepository;
		this.cloudinaryService = cloudinaryService;
	}

	@Override
	public Restaurant getRestaurant(Long id) {
		return rr.getOne(id);
	}
	@Override
	public Restaurant getByOwner(User owner) {
		return rr.findByOwner(owner);
	}

	@Override
	public Restaurant getLoggedInRestaurant() {
		String loggedInUser=securityService.findLoggedInUsername();
		Restaurant restaurant=rr.findByOwner(userService.findByUsername(loggedInUser));
		return restaurant;
	}

	@Override
	public String getLoggedInRestaurantUsername() {
		return securityService.findLoggedInUsername();
	}

	@Override
	public void saveTableNames(List<TableNameDto> dtoList) {
		TableNameEntityConverter.convert(dtoList).forEach(entity->{
			tableNameRepository.save(entity);
		});
	}

	@Override
	public List<TableNameResponse> getTableNames(Restaurant restaurant) {
		List<TableName> tableNames = tableNameRepository.findTableNamesByRestaurantOrderByMasaNoAsc(restaurant);
			return TableNameResponseConverter.convert(tableNames);
	}

	@Override
	public Optional<TableName> getTableName(Restaurant restaurant,Integer masaNo){
		return Optional.ofNullable(tableNameRepository.findByRestaurantAndMasaNo(restaurant,masaNo));
	}

	@Override
	public void saveRestaurantLogo(LogoDto logoDto) {
		Restaurant restaurant = getLoggedInRestaurant();
		String oldImageId = restaurant.getLogoPublicId();
		if(logoDto.getShouldDelImage()==true && oldImageId!=null){
			cloudinaryService.deleteFile(oldImageId);
			restaurant.setLogoPublicId(null);
		}
		if(logoDto.getLogo()!=null && !logoDto.getLogo().isEmpty() && logoDto.getShouldDelImage()==false){
			String newLogoId;
			if(oldImageId==null){
				newLogoId = cloudinaryService.uploadFile(logoDto.getLogo());
			}else{
				newLogoId = cloudinaryService.updateFile(logoDto.getLogo(),oldImageId);
			}
			restaurant.setLogoPublicId(newLogoId);
		}
		rr.save(restaurant);
	}

	@Override
	public TableNameRequest getTableNameRequest(Restaurant restaurant){
		List<TableName> tableNames=tableNameRepository.findTableNamesByRestaurantOrderByMasaNoAsc(restaurant);
		TableNameRequest request=new TableNameRequest();
		request.setRequestItemList(TableNameResponseConverter.convertToRequest(tableNames));
		return request;
	}


}

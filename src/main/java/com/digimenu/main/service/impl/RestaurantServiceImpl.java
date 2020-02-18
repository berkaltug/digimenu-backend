package com.digimenu.main.service.impl;

import com.digimenu.main.domain.converter.TableNameEntityConverter;
import com.digimenu.main.domain.converter.TableNameResponseConverter;
import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.TableName;
import com.digimenu.main.domain.response.TableNameResponse;
import com.digimenu.main.repository.TableNameRepository;
import com.digimenu.main.service.RestaurantService;
import com.digimenu.main.service.SecurityService;
import com.digimenu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.RestaurantRepository;
import com.digimenu.main.security.User;

import java.util.List;
import java.util.Optional;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	private RestaurantRepository rr;
	private SecurityService securityService;
	private UserService userService;
	private TableNameRepository tableNameRepository;
	@Autowired
	public RestaurantServiceImpl(RestaurantRepository rr, SecurityService securityService, UserService userService, TableNameRepository tableNameRepository) {
		this.rr = rr;
		this.securityService = securityService;
		this.userService = userService;
		this.tableNameRepository = tableNameRepository;
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
		tableNameRepository.saveAll(TableNameEntityConverter.convert(dtoList));

	}

	@Override
	public Optional<List<TableNameResponse>> getTableNames(Restaurant restaurant) {
		Optional<List<TableName>> tableNames = Optional.of(tableNameRepository.findTableNamesByRestaurant(restaurant));
		if(tableNames.isPresent()) {
			return Optional.of(TableNameResponseConverter.convert(tableNames.get()));
		}else{
			return Optional.empty();
		}
	}

}

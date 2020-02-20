package com.digimenu.main.service;

import com.digimenu.main.domain.dto.TableNameDto;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.entity.TableName;
import com.digimenu.main.domain.request.TableNameRequest;
import com.digimenu.main.domain.response.TableNameResponse;
import com.digimenu.main.security.User;

import java.util.List;
import java.util.Optional;

public interface RestaurantService {
	Restaurant getRestaurant(Long id);
	Restaurant getByOwner(User owner);
	Restaurant getLoggedInRestaurant();
	String getLoggedInRestaurantUsername();
	void saveTableNames(List<TableNameDto> dto);
	List<TableNameResponse> getTableNames(Restaurant restaurant);
	TableNameRequest getTableNameRequest(Restaurant restaurant);
	Optional<TableName> getTableName(Restaurant restaurant,Integer masaNo);
}

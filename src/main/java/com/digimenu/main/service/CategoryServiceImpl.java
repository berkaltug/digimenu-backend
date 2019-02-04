package com.digimenu.main.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digimenu.main.entity.Category;
import com.digimenu.main.repository.CategoryRepository;
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryRepository catr;
	@Override
	public Category getCategory(Long id) {
		return catr.getOne(id);
	}

	@Override
	public void deleteCategory(Long id) {
		catr.delete(catr.getOne(id));
	}

	@Override
	public Category updateCategory(Category cat) {
		Category newCat = new Category(cat.getId(),cat.getName(),cat.getRestaurants());
		catr.delete(cat);
		return catr.save(newCat);
	}

	@Override
	public void addCategory(Category cat) {
		catr.save(cat);
	}

}

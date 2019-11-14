package com.digimenu.main.service;

import com.digimenu.main.entity.Category;

import java.util.Collection;

public interface CategoryService {
	Category getCategory(Long id);
	void deleteCategory(Long id);
	Category updateCategory(Category cat);
	void addCategory(Category cat);
	Collection<Category> getCategories();
}

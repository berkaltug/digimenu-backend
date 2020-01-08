package com.digimenu.main.service.impl;

import com.digimenu.main.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.digimenu.main.domain.entity.Category;
import com.digimenu.main.repository.CategoryRepository;

import java.util.Collection;

@Service
public class CategoryServiceImpl implements CategoryService {
	private CategoryRepository catr;

	@Autowired
	public CategoryServiceImpl(CategoryRepository catr) {
		this.catr = catr;
	}

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
		Category newCat = new Category(cat.getId(),cat.getName());
		catr.delete(cat);
		return catr.save(newCat);
	}

	@Override
	public void addCategory(Category cat) {
		catr.save(cat);
	}

	@Override
	public Collection<Category> getCategories() {
		return catr.findAll(Sort.by("name"));
	}

}

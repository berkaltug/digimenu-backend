package com.digimenu.main.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digimenu.main.domain.entity.Category;

public interface CategoryRepository extends JpaRepository<Category,Long>{

}

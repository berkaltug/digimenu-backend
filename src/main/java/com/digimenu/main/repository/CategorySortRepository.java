package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.CategorySort;
import com.digimenu.main.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategorySortRepository extends JpaRepository<CategorySort,Long> {
    List<CategorySort> findByRestaurantOrderBySortingNoAsc(Restaurant restaurant);
}

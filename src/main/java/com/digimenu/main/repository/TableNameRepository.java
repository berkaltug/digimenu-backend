package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.domain.entity.TableName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TableNameRepository extends JpaRepository<TableName,Long> {
    List<TableName> findTableNamesByRestaurant(Restaurant restaurant_id);
}

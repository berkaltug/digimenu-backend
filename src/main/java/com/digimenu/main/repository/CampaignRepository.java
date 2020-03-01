package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CampaignRepository extends JpaRepository<Campaign,Long> {
    List<Campaign> findAllByRestaurant(Restaurant restaurant);
}

package com.digimenu.main.service;

import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;

import java.util.List;

public interface CampaignService {

    Campaign getCampaign(Long id);
    void deleteCampaign(Long id);
    Campaign updateCampaign(Campaign campaign);
    Campaign addCampaign(Campaign campaign);
    List<Campaign> getAllCampaignsByRestaurant(Restaurant restaurant);
}

package com.digimenu.main.service;

import com.digimenu.main.domain.dto.PanelCampaignDto;
import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;

import java.util.List;
import java.util.Optional;

public interface CampaignService {

    Campaign getCampaign(Long id);
    void deleteCampaign(Long id);
    Campaign updateCampaign(PanelCampaignDto panelCampaignDto);
    Campaign updateCampaign(Campaign campaign);
    Campaign addCampaign(PanelCampaignDto panelCampaignDto);
    List<Campaign> getAllCampaignsByRestaurant(Restaurant restaurant);
    Optional<Campaign> getByNameAndRestaurant(Restaurant restaurant, String name);
}

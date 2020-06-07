package com.digimenu.main.service.impl;

import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.CampaignRepository;
import com.digimenu.main.service.CampaignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository) {
        this.campaignRepository = campaignRepository;
    }

    @Override
    public Campaign getCampaign(Long id) {
       return campaignRepository.findById(id).get();
    }

    @Override
    public void deleteCampaign(Long id) {
        campaignRepository.deleteById(id);
    }

    @Override
    public Campaign updateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign addCampaign(Campaign campaign) {
        campaign.setRating(0f);
        campaign.setVoteCount(0);
        return campaignRepository.save(campaign);
    }

    @Override
    public List<Campaign> getAllCampaignsByRestaurant(Restaurant restaurant){
        return campaignRepository.findAllByRestaurant(restaurant);
    }

    @Override
    public Optional<Campaign> getByNameAndRestaurant(Restaurant restaurant,String name){
        return campaignRepository.findByRestaurantAndName(restaurant,name);
    }
}

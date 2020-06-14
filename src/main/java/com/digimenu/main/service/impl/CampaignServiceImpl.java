package com.digimenu.main.service.impl;

import com.digimenu.main.domain.converter.PanelCampaignConverter;
import com.digimenu.main.domain.dto.PanelCampaignDto;
import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Restaurant;
import com.digimenu.main.repository.CampaignRepository;
import com.digimenu.main.service.CampaignService;
import com.digimenu.main.service.CloudinaryService;
import com.digimenu.main.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CampaignServiceImpl implements CampaignService {

    private CampaignRepository campaignRepository;
    private RestaurantService restaurantService;
    private CloudinaryService cloudinaryService;

    @Autowired
    public CampaignServiceImpl(CampaignRepository campaignRepository, RestaurantService restaurantService,CloudinaryService cloudinaryService) {
        this.campaignRepository = campaignRepository;
        this.restaurantService = restaurantService;
        this.cloudinaryService = cloudinaryService;
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
    public Campaign updateCampaign(PanelCampaignDto panelCampaignDto) {
        Campaign campaign = PanelCampaignConverter.convert(panelCampaignDto);
        campaign.setRestaurant(restaurantService.getRestaurant(panelCampaignDto.getRestaurantId()));
        String oldImageId = getCampaign(panelCampaignDto.getId()).getImagePublicId();
        String newImageId ;
        if(panelCampaignDto.getImage()!=null && !panelCampaignDto.getImage().isEmpty() && panelCampaignDto.getShouldDelImage()==false){
            newImageId = cloudinaryService.updateFile(panelCampaignDto.getImage(),oldImageId);
            campaign.setImagePublicId(newImageId);
        }else if(panelCampaignDto.getShouldDelImage()==true){
            cloudinaryService.deleteFile(oldImageId);
        }
        else{
            campaign.setImagePublicId(oldImageId);
        }
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign updateCampaign(Campaign campaign) {
        return campaignRepository.save(campaign);
    }

    @Override
    public Campaign addCampaign(PanelCampaignDto panelCampaignDto) {
        Campaign campaign= PanelCampaignConverter.convert(panelCampaignDto);
        campaign.setRestaurant(restaurantService.getLoggedInRestaurant());
        if(panelCampaignDto.getImage()!=null && !panelCampaignDto.getImage().isEmpty()){
            campaign.setImagePublicId(cloudinaryService.uploadFile(panelCampaignDto.getImage()));
        }
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

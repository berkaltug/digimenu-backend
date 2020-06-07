package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.PanelCampaignDto;
import com.digimenu.main.domain.entity.Campaign;

public class PanelCampaignConverter {
    public static Campaign convert(PanelCampaignDto dto){
        Campaign campaign=new Campaign();
        campaign.setId(dto.getId());
        campaign.setRating(dto.getRating());
        campaign.setActive(dto.getActive());
        campaign.setContents(dto.getContents());
        campaign.setPrice(dto.getPrice());
        campaign.setName(dto.getName());
        campaign.setVoteCount(dto.getVoteCount());
        return campaign;
    }

    public static PanelCampaignDto convert(Campaign campaign){
        PanelCampaignDto dto=new PanelCampaignDto();
        dto.setId(campaign.getId());
        dto.setName(campaign.getName());
        dto.setContents(campaign.getContents());
        dto.setActive(campaign.getActive());
        dto.setPrice(campaign.getPrice());
        dto.setRating(campaign.getRating());
        dto.setRestaurantId(campaign.getRestaurant().getId());
        dto.setVoteCount(campaign.getVoteCount());
        return dto;
    }
}

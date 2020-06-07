package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.response.CampaignResponseItem;

public class CampaignResponseItemConverter {

    public static CampaignResponseItem convert(Campaign campaign){
        CampaignResponseItem item = new CampaignResponseItem();
        item.setId(campaign.getId());
        item.setContents(campaign.getContents());
        item.setName(campaign.getName());
        item.setPrice(campaign.getPrice());
        item.setImagePublicId(campaign.getImagePublicId());
        return item;
    }
}

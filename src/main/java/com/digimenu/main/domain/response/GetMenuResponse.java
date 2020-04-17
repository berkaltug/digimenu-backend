package com.digimenu.main.domain.response;

import com.digimenu.main.domain.dto.MenuDto;
import com.digimenu.main.domain.entity.Campaign;
import com.digimenu.main.domain.entity.Menu;

import java.util.List;
import java.util.Objects;

public class GetMenuResponse {
    List<MenuResponseItem> items;
    List<MenuResponseItem> favourites;
    List<CampaignResponseItem> campaigns;

    public List<MenuResponseItem> getItems() {
        return items;
    }

    public void setItems(List<MenuResponseItem> items) {
        this.items = items;
    }

    public List<MenuResponseItem> getFavourites() {
        return favourites;
    }

    public void setFavourites(List<MenuResponseItem> favourites) {
        this.favourites = favourites;
    }

    public List<CampaignResponseItem> getCampaigns() {
        return campaigns;
    }

    public void setCampaigns(List<CampaignResponseItem> campaigns) {
        this.campaigns = campaigns;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GetMenuResponse that = (GetMenuResponse) o;
        return Objects.equals(items, that.items) &&
                Objects.equals(favourites, that.favourites) &&
                Objects.equals(campaigns, that.campaigns);
    }

    @Override
    public int hashCode() {
        return Objects.hash(items, favourites, campaigns);
    }

    @Override
    public String toString() {
        return "GetMenuResponse{" +
                "items=" + items +
                ", favourites=" + favourites +
                ", campaigns=" + campaigns +
                '}';
    }
}

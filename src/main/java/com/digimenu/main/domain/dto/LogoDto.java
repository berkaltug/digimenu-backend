package com.digimenu.main.domain.dto;

import org.springframework.web.multipart.MultipartFile;

public class LogoDto {
    private MultipartFile logo;
    private Boolean shouldDelImage;
    public MultipartFile getLogo() {
        return logo;
    }

    public void setLogo(MultipartFile logo) {
        this.logo = logo;
    }

    public Boolean getShouldDelImage() {
        return shouldDelImage;
    }

    public void setShouldDelImage(Boolean shouldDelImage) {
        this.shouldDelImage = shouldDelImage;
    }
}

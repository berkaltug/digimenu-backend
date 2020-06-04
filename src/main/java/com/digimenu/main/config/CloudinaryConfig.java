package com.digimenu.main.config;


import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CloudinaryConfig {
    @Value("${cloudinary.cloud_name}")
    private String myCloudName;
    @Value("${cloudinary.api_key}")
    private String myApiKey;
    @Value("${cloudinary.api_secret}")
    private String myApiSecret;

    @Bean
    public Cloudinary makeCloudinary(){
        Cloudinary cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", myCloudName,
                "api_key", myApiKey,
                "api_secret", myApiSecret));

        return cloudinary;
    }
}

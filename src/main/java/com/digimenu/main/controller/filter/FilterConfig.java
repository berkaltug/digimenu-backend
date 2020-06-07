package com.digimenu.main.controller.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<FileSizeFilter> fileSizeFilter(){
        FilterRegistrationBean<FileSizeFilter> registerBean= new FilterRegistrationBean<>();
        registerBean.setFilter(new FileSizeFilter());
        registerBean.setUrlPatterns(Arrays.asList("/restaurant/additem","/restaurant/updateitem","/restaurant/createCampaign","/restaurant/updateCampaign"));
        return registerBean;
    }
}

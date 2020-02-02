package com.digimenu.main.service.impl;

import com.digimenu.main.service.MapsService;
import org.springframework.stereotype.Service;

@Service
public class MapsServiceImpl implements MapsService {
    @Override
    public boolean checkHaversineDistance(Double lat1, Double long1, Double lat2, Double long2, Double radius) {
        double R=6371;
        double dLat= degToRad(lat2-lat1);
        double dLon= degToRad(long2-long1);
        double a =  Math.sin(dLat/2) * Math.sin(dLat/2) + Math.cos(degToRad(lat1)) * Math.cos(degToRad(lat2)) * Math.sin(dLon/2) * Math.sin(dLon/2);
        double c = Math.atan2(Math.sqrt(a),Math.sqrt(1-a));
        double d = R * c * 1000 ;// metre cinsinden olsun diye *1000
        return radius >= d ? true : false ;
    }

    private Double degToRad(Double deg){
        return deg * (Math.PI/180);
    }
}

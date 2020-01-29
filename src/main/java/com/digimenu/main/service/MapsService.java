package com.digimenu.main.service;

import org.springframework.stereotype.Service;


public interface MapsService {
    boolean checkHaversineDistance(Double lat1,Double long1,Double lat2,Double long2,Double Radius);
}

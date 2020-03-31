package com.digimenu.main.domain.projection;

import java.util.Date;

public interface PastOrdersProjection {
    Integer getCount();
    String getName();
    Long getRestaurantId();
    String getRestaurantName();
    Date getOrderDate();
}

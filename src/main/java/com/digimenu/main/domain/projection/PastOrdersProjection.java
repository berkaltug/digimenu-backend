package com.digimenu.main.domain.projection;

import java.math.BigDecimal;
import java.util.Date;

public interface PastOrdersProjection {
    Integer getCount();
    BigDecimal getTotal();
    String getName();
    Long getRestaurantId();
    String getRestaurantName();
    Date getOrderDate();
}

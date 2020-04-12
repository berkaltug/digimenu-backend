package com.digimenu.main.service;

import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.domain.response.PastOrdersResponse;

import java.util.List;
import java.util.Optional;

public interface SiparisService {
    Optional<Siparis> getOrder(Long id);
    void insertOrder(Siparis siparis);
    PastOrdersResponse pastOrders();
}

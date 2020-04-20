package com.digimenu.main.service.impl;

import com.digimenu.main.domain.converter.PastOrdersResponseConverter;
import com.digimenu.main.domain.dto.PastOrdersResponseDto;
import com.digimenu.main.domain.entity.Siparis;
import com.digimenu.main.domain.response.PastOrdersResponse;
import com.digimenu.main.repository.SiparisRepository;
import com.digimenu.main.security.User;
import com.digimenu.main.service.SiparisService;
import com.digimenu.main.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
public class SiparisServiceImpl implements SiparisService {

    private SiparisRepository siparisRepository;
    private UserService userService;

    @Autowired
    public SiparisServiceImpl(SiparisRepository siparisRepository, UserService userService) {
        this.siparisRepository = siparisRepository;
        this.userService = userService;
    }

    @Override
    public Optional<Siparis> getOrder(Long id) {
        return siparisRepository.findById(id);
    }

    @Override
    public void insertOrder(Siparis siparis) {
        siparisRepository.save(siparis);
    }

    @Override
    public PastOrdersResponse pastOrders() {
        PastOrdersResponse response = PastOrdersResponseConverter.convert(siparisRepository.findAllByUser(userService.findLoggedInUser()));
        response.getPastOrders().sort(Comparator.comparing(PastOrdersResponseDto::getOrderDate));
        return response;
    }
}

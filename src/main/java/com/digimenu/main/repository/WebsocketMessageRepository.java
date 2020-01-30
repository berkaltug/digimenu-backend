package com.digimenu.main.repository;

import com.digimenu.main.domain.entity.WebsocketMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WebsocketMessageRepository extends JpaRepository<WebsocketMessage,Integer> {
    List<WebsocketMessage> findAllByRestaurantId(Long id);
}

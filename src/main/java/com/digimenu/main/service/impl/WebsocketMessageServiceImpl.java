package com.digimenu.main.service.impl;

import com.digimenu.main.domain.entity.WebsocketMessage;
import com.digimenu.main.repository.WebsocketMessageRepository;
import com.digimenu.main.service.WebsocketMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebsocketMessageServiceImpl implements WebsocketMessageService {

    private WebsocketMessageRepository websocketMessageRepository;

    @Autowired
    public WebsocketMessageServiceImpl(WebsocketMessageRepository websocketMessageRepository) {
        this.websocketMessageRepository = websocketMessageRepository;
    }

    @Override
    public WebsocketMessage getMessage(Integer id) {
        return websocketMessageRepository.getOne(id);
    }
    @Override
    public List<WebsocketMessage> getAllMessages(Long id){
        return websocketMessageRepository.findAllByRestaurantId(id);
    }
    @Override
    public void deleteMessage(Integer id) {
        websocketMessageRepository.deleteById(id);
    }

    @Override
    public WebsocketMessage insertMessage(WebsocketMessage message){
        return websocketMessageRepository.save(message);
    }
}

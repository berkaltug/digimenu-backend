package com.digimenu.main.service;

import com.digimenu.main.domain.entity.WebsocketMessage;

import java.util.List;

public interface WebsocketMessageService {
    WebsocketMessage getMessage(Integer id);
    void deleteMessage(Integer id);
    WebsocketMessage insertMessage(WebsocketMessage message);
    List<WebsocketMessage> getAllMessages(Long id);
}

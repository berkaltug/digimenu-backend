package com.digimenu.main.domain.converter;

import com.digimenu.main.domain.dto.MessageDto;
import com.digimenu.main.domain.entity.WebsocketMessage;

public class MessageDtoConverter {
    public static MessageDto convert(WebsocketMessage message){
        MessageDto dto=new MessageDto();
        dto.setMessageId(message.getId());
        dto.setMessage(message.getMessage());
        dto.setMasaNo(message.getMasaNo());
        return dto;
    }
}

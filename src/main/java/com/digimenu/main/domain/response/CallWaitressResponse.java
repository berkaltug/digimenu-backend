package com.digimenu.main.domain.response;

import com.digimenu.main.domain.dto.MessageDto;

import java.util.Objects;

public class CallWaitressResponse {
    String username;
    MessageDto messageDto;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public MessageDto getMessageDto() {
        return messageDto;
    }

    public void setMessageDto(MessageDto messageDto) {
        this.messageDto = messageDto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CallWaitressResponse that = (CallWaitressResponse) o;
        return Objects.equals(username, that.username) &&
                Objects.equals(messageDto, that.messageDto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, messageDto);
    }

    @Override
    public String toString() {
        return "CallWaitressResponse{" +
                "username='" + username + '\'' +
                ", messageDto=" + messageDto +
                '}';
    }
}

package com.digimenu.main.domain.dto;

import java.util.Objects;

public class MessageDto {
    private Integer messageId;
    private String message;
    private Integer masaNo;

    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getMasaNo() {
        return masaNo;
    }

    public void setMasaNo(Integer masaNo) {
        this.masaNo = masaNo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MessageDto that = (MessageDto) o;
        return Objects.equals(messageId, that.messageId) &&
                Objects.equals(message, that.message) &&
                Objects.equals(masaNo, that.masaNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(messageId, message, masaNo);
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "messageId=" + messageId +
                ", message='" + message + '\'' +
                ", masaNo=" + masaNo +
                '}';
    }
}

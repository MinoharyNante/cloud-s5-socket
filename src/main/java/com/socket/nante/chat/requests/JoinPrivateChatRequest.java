package com.socket.nante.chat.requests;

import com.socket.nante.models.customPagination.CustomPagination;

public class JoinPrivateChatRequest {
    private String chatId;
    private CustomPagination pagination;
    int extraSkip;

    public String getChatId() {
        return chatId;
    }

    public void setChatId(String chatId) {
        this.chatId = chatId;
    }

    public CustomPagination getPagination() {
        return pagination;
    }

    public void setPagination(CustomPagination pagination) {
        this.pagination = pagination;
    }

    public int getExtraSkip() {
        return extraSkip;
    }

    public void setExtraSkip(int extraSkip) {
        this.extraSkip = extraSkip;
    }
}
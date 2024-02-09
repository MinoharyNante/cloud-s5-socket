package com.socket.nante.chat.requests;

public class ChatMessageRequest {

    private String message;
    private String discussionId;
    private int idExpedit;

    public ChatMessageRequest() {
    }

    public ChatMessageRequest(String discussionId, String message, int idExpedit) {
        super();
        this.discussionId = discussionId;
        this.message = message;
        this.idExpedit = idExpedit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "userName='" + discussionId + '\'' +
                ", message='" + message + '\'' +
                ", idExpedit='" + idExpedit + '\'' +
                '}';
    }

    public String getDiscussionId() {
        return discussionId;
    }

    public void setDiscussionId(String discussionId) {
        this.discussionId = discussionId;
    }

    public int getIdExpedit() {
        return idExpedit;
    }

    public void setIdExpedit(int idExpedit) {
        this.idExpedit = idExpedit;
    }

}

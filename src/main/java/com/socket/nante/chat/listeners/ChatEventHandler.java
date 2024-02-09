package com.socket.nante.chat.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.socket.nante.models.message.Message;

// ChatEventHandler.java
// @Component
public class ChatEventHandler implements DataListener<Message> {

    @Autowired
    private SocketIOServer socketIoServer;

    @Override
    public void onData(SocketIOClient client, Message message, AckRequest ackRequest) {
        String channelId = message.getIdDiscussion();
        socketIoServer.getRoomOperations(channelId).sendEvent("messageReceived", message);
    }
}

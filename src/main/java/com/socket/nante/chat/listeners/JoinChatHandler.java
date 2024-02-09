package com.socket.nante.chat.listeners;

import org.springframework.beans.factory.annotation.Autowired;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;
import com.socket.nante.chat.requests.JoinPrivateChatRequest;

public class JoinChatHandler implements DataListener<JoinPrivateChatRequest> {
    @Autowired
    SocketIOServer socketIoServer;

    @Override
    public void onData(SocketIOClient client, JoinPrivateChatRequest data, AckRequest ackSender) throws Exception {
        client.joinRoom(data.getChatId());
        System.out.println("joined");
        socketIoServer.getRoomOperations(data.getChatId()).sendEvent("joined", data);
    }
}

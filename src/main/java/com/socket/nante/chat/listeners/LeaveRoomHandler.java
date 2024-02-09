package com.socket.nante.chat.listeners;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.listener.DataListener;
import com.socket.nante.chat.requests.JoinPrivateChatRequest;

public class LeaveRoomHandler implements DataListener<JoinPrivateChatRequest> {
    @Override
    public void onData(SocketIOClient client, JoinPrivateChatRequest data, AckRequest ackSender) throws Exception {
        System.out.println(client.getSessionId() + "left chat");
        client.leaveRoom(data.getChatId());
    }
}

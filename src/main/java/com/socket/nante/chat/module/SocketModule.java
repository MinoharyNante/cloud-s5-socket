package com.socket.nante.chat.module;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.ConnectListener;
import com.corundumstudio.socketio.listener.DataListener;
import com.socket.nante.chat.requests.ChatMessageRequest;
import com.socket.nante.chat.requests.CreatePrivateChatRequest;
import com.socket.nante.chat.requests.JoinPrivateChatRequest;
import com.socket.nante.chat.service.SocketService;

@Component
public class SocketModule {

    @Autowired
    private SocketIOServer server;

    @Autowired
    private SocketService socketService;

    public SocketModule(SocketIOServer server, SocketService socketService) {
        this.server = server;
        this.socketService = socketService;
        server.addConnectListener(this.onConnected());
        // server.addDisconnectListener(this.onDisconnected());
        server.addEventListener("send_message", ChatMessageRequest.class, this.onChatReceived());
        server.addEventListener("create_chat", CreatePrivateChatRequest.class, onCreatePrivateChat());
        server.addEventListener("join_chat", JoinPrivateChatRequest.class, onJoinChat());
    }

    private DataListener<JoinPrivateChatRequest> onJoinChat() {
        return (senderClient, data, ackSender) -> {
            System.out.println(data.toString());
            socketService.joinDiscussion(senderClient, data);
        };
    }

    private DataListener<CreatePrivateChatRequest> onCreatePrivateChat() {
        return (senderClient, data, ackSender) -> {
            System.out.println(data.toString());
            socketService.createDiscussion(server, senderClient, data);
        };
    }

    private DataListener<ChatMessageRequest> onChatReceived() {
        return (senderClient, data, ackSender) -> {
            System.out.println(data.toString());
            socketService.saveMessage(server, senderClient, data);
        };
    }

    private ConnectListener onConnected() {
        return (client) -> {
            // var params = client.getHandshakeData().getUrlParams();
            // String room = params.get("room").stream().collect(Collectors.joining());
            // String username =
            // params.get("username").stream().collect(Collectors.joining());
            // client.joinRoom(room);
            // socketService.saveInfoMessage(client, String.format(Constant.WELCOME_MESSAGE,
            // username), room);
            // log.info("Socket ID[{}] - room[{}] - username [{}] Connected to chat module
            // through", client.getSessionId().toString(), room, username);
        };
    }

    // private DisconnectListener onDisconnected() {
    // return client -> {
    // var params = client.getHandshakeData().getUrlParams();
    // String room = params.get("room").stream().collect(Collectors.joining());
    // String username =
    // params.get("username").stream().collect(Collectors.joining());
    // // socketService.saveInfoMessage(client,
    // String.format(Constant.DISCONNECT_MESSAGE, username), room);
    // log.info("Socket ID[{}] - room[{}] - username [{}] discnnected to chat module
    // through", client.getSessionId().toString(), room, username);
    // };
    // }

}

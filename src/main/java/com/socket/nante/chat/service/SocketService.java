package com.socket.nante.chat.service;

import org.springframework.stereotype.Service;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.socket.nante.chat.requests.ChatMessageRequest;
import com.socket.nante.chat.requests.CreatePrivateChatRequest;
import com.socket.nante.chat.requests.JoinPrivateChatRequest;

@Service
public class SocketService {

    public void sendSocketmessage(SocketIOServer server, SocketIOClient sender, ChatMessageRequest message)
            throws Exception {
        server.getRoomOperations(message.getDiscussionId()).getClients().stream().forEach(client -> {
            if (client.getSessionId().compareTo(sender.getSessionId()) != 0) {
                message.setIdExpedit(message.getIdExpedit());
                client.sendEvent("message_received", message);
                client.sendEvent("new_message", message);
                System.out.print("client send event to " + client.getRemoteAddress().toString());
            }
        });

    }

    public void createDiscussion(SocketIOServer server, SocketIOClient client, CreatePrivateChatRequest data)
            throws Exception {

        if (data != null) {
            client.joinRoom(data.getDiscussionId());
            server.getRoomOperations(data.getDiscussionId()).sendEvent("created", data);
        } else {
            // Handle the case where data is null
            System.out.println("data is null");
        }
    }

    public void joinDiscussion(SocketIOClient client, JoinPrivateChatRequest request) {
        client.joinRoom(request.getChatId());
    }

    public void saveMessage(SocketIOServer server, SocketIOClient sender, ChatMessageRequest message) throws Exception {
        sendSocketmessage(server, sender, message);
    }

}

package ru.college.collegeapi.sockets;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.time.LocalDateTime;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        MyStorage.sessions.put(session.getId(), session);
        MyStorage.timeouts.put(session.getId(), LocalDateTime.now());
        super.afterConnectionEstablished(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        MyStorage.timeouts.put(session.getId(), LocalDateTime.now());
        super.handleTextMessage(session, message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        MyStorage.sessions.remove(session.getId());
        MyStorage.timeouts.remove(session.getId());
        super.afterConnectionClosed(session, status);
    }
}

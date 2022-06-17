package ru.college.collegeapi.sockets;

import org.springframework.web.socket.WebSocketSession;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MyStorage {

    public static Map<String, WebSocketSession> sessions = new ConcurrentHashMap<>();
    public static Map<String, LocalDateTime> timeouts = new ConcurrentHashMap<>();

}

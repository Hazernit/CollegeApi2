package ru.college.collegeapi.controller;

import org.aspectj.weaver.ast.Not;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import ru.college.collegeapi.model.Notification;

@Controller
public class NotificationWebSocketController {

    @MessageMapping("/hello")
    @SendTo("/topic/notify")
    public Notification send(Notification notification){
        return notification;
    }
}

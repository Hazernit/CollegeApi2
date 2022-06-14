package ru.college.collegeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.college.collegeapi.model.Notification;

@Component
public class NotificationSchedulerService {
    private SimpMessagingTemplate template;

    @Autowired
    public void setTemplate(SimpMessagingTemplate template) {
        this.template = template;
    }

    @Scheduled(fixedDelay = 2000)
    public void sendPing() {
        template.convertAndSend("/topic/notify", new Notification(null, "ping", "", null, null));
    }
}

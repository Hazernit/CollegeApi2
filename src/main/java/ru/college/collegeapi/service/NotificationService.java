package ru.college.collegeapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.TextMessage;
import ru.college.collegeapi.model.Notification;
import ru.college.collegeapi.repository.NotificationRepository;
import ru.college.collegeapi.sockets.MyStorage;

import java.io.IOException;
import java.util.List;

@Service
public class NotificationService {

    @Autowired
    private NotificationRepository notificationRepository;

    public List<Notification> getAll() {
        return notificationRepository.findAll();
    }

    public Notification save(Notification notification) {
        return notificationRepository.save(notification);
    }

    public void delete(Long id) {
        notificationRepository.deleteById(id);
    }

    public void sendToClients(Notification notification){
        MyStorage.sessions.values().forEach(
                s-> {
                    try {
                        s.sendMessage(new TextMessage(notification.getContent()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );
    }


}

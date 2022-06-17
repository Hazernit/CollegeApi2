package ru.college.collegeapi.sockets;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class MyScheduler {

    @Scheduled(fixedDelay = 2000)
    public void doPing() {
        List<String> sessionToDelete = new ArrayList<>();
        MyStorage.sessions.values().forEach(s -> {
            LocalDateTime lastTime = MyStorage.timeouts.get(s.getId());
            if (lastTime.plusSeconds(8).isBefore(LocalDateTime.now())) {
                try {
                    sessionToDelete.add(s.getId());
                    s.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    s.sendMessage(new TextMessage("PING"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
        sessionToDelete.forEach(
                f -> {
                    MyStorage.timeouts.remove(f);
                    MyStorage.sessions.remove(f);
                }
        );
    }
}

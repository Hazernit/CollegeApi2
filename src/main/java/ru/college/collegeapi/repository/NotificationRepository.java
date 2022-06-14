package ru.college.collegeapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.college.collegeapi.model.Notification;

public interface NotificationRepository extends JpaRepository<Notification, Long> {



}

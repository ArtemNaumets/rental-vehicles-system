package com.naumets.notification.kafka;

import com.naumets.notification.email.EmailService;
import com.naumets.notification.enums.NotificationType;
import com.naumets.notification.models.Notification;
import com.naumets.notification.repositories.NotificationRepository;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics = "vehicle-hire-topic")
    public void consumeHireConfirmationNotification(HireConfirmation hireConfirmation) throws MessagingException {
        log.info(String.format("Consumer: %s", hireConfirmation ));
        notificationRepository.save(Notification.builder()
                        .notificationType(NotificationType.HIRE_CONFIRMATION)
                        .notificationDate(LocalDateTime.now())
                        .hireConfirmation(hireConfirmation)
                .build());

        String clientName = hireConfirmation.client().getName();
        String clientEmail = hireConfirmation.client().getEmail();
        Date dateIn = hireConfirmation.dateIn();
        Date dateOut = hireConfirmation.dateOut();
        String price = hireConfirmation.price();
        String vehicleName = hireConfirmation.vehicle().getName();
        String vehicleNumber = hireConfirmation.vehicle().getVehicleNumber();

        emailService.sentHireEmail(clientEmail,clientName,dateIn,dateOut,price,vehicleName,vehicleNumber);


    }
}

package com.naumets.notification.models;

import com.naumets.notification.enums.NotificationType;
import com.naumets.notification.kafka.HireConfirmation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Notification  {

    @Id
    private String id;

    private NotificationType notificationType;
    private LocalDateTime notificationDate;
    private HireConfirmation hireConfirmation;


}

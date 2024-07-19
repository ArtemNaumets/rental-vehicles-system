package com.naumets.notification.email;


import com.naumets.notification.kafka.HireConfirmation;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private SpringTemplateEngine templateEngine;

    @Async
    public void sentHireEmail(
            String destinationEmail,
            String clientName,
            Date dateIn,
            Date dateOut,
            String price,
            String vehicleName,
            String vehicleNumber


    ) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(
                mimeMessage,
                MimeMessageHelper.MULTIPART_MODE_RELATED,
                StandardCharsets.UTF_8.name()
        );
        mimeMessageHelper.setFrom("noreply@example.com");

        final String templateName = EmailTemplates.HIRE_CONFIRMATION.getTemplate();

        Map<String, Object> vars = new HashMap<>();
        vars.put("clientName", clientName);
        vars.put("dateIn", dateIn);
        vars.put("dateOut", dateOut);
        vars.put("price", price);
        vars.put("vehicleName", vehicleName);
        vars.put("vehicleNumber", vehicleNumber);


        Context context = new Context();
        context.setVariables(vars);
        mimeMessageHelper.setSubject(EmailTemplates.HIRE_CONFIRMATION.getSubject());

        try {
            String htmlTemplate = templateEngine.process(templateName, context);
            mimeMessageHelper.setText(htmlTemplate, true);

            mimeMessageHelper.setTo(destinationEmail);
            mailSender.send(mimeMessage);
            System.out.println("Email sent " + destinationEmail);
        }catch (MessagingException e){
            log.warn("Warning - cant send mail: " + destinationEmail);
        }
    }
}

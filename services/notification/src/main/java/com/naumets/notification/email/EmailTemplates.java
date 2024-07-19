package com.naumets.notification.email;

import lombok.Getter;

public enum EmailTemplates {
    HIRE_CONFIRMATION("hire-confirmation.html", "Hire successfully processed");

    @Getter
    private final String template;

    @Getter
    private final String subject;

    EmailTemplates(String template, String subject) {
        this.template = template;
        this.subject = subject;
    }
}

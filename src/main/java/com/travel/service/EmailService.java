
package com.travel.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Value("${app.mail.to:reves.indochine@gmail.com}")
    private String toEmail;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendContactEmail(
            String fullName,
            String email,
            String phoneNumber,
            String subject,
            String message,
            String nationality,
            String contactMethod,
            String hearFrom
    ) {
        SimpleMailMessage mail = new SimpleMailMessage();

        mail.setFrom(fromEmail);
        mail.setTo(toEmail);
        mail.setSubject("[Reves Indochine] " + subject);

        mail.setText(
                "NEW CONTACT MESSAGE\n\n"
                + "Full name: " + value(fullName) + "\n"
                + "Email: " + value(email) + "\n"
                + "Phone: " + value(phoneNumber) + "\n"
                + "Nationality: " + value(nationality) + "\n"
                + "Contact method: " + value(contactMethod) + "\n"
                + "Heard from: " + value(hearFrom) + "\n\n"
                + "Subject: " + value(subject) + "\n\n"
                + "Message:\n" + value(message)
        );

        mailSender.send(mail);
    }

    private String value(String input) {
        return input == null || input.isBlank()
                ? "Not provided"
                : input;
    }
}

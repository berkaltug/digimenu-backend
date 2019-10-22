package com.digimenu.main.service;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class SendGridMailService {
    private SendGrid sendGrid;

    @Autowired
    public SendGridMailService(SendGrid sendGrid) {
        this.sendGrid = sendGrid;
    }

    public void sendEmail(String from, String to, String subject, Content content) {

        Mail mail = new Mail(new Email(from), subject, new Email(to), content);
        mail.setReplyTo(new Email("digimenuinfo@gmail.com"));
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            this.sendGrid.api(request);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
    }
}

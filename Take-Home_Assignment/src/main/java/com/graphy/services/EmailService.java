package com.graphy.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.graphy.models.User;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {
	
	@Autowired
	private JavaMailSender emailSender;
	
	public boolean sendEmail(String recipientEmail, String subject, String content) {
        
		boolean flag=false;
		try {
            MimeMessage message = emailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setTo(recipientEmail);
            helper.setSubject(subject);
            helper.setText(content, true);
            emailSender.send(message);
            System.out.println("Email sent successfully to: " + recipientEmail);
            flag=true;
        } catch (MessagingException e) {
            System.out.println("Failed to send email to: " + recipientEmail);
            e.printStackTrace();
        }
		return flag;
    }

}

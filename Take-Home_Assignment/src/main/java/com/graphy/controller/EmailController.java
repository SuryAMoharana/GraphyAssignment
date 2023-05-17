package com.graphy.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.graphy.models.CSVLogger;
import com.graphy.models.User;
import com.graphy.repo.UserRepository;
import com.graphy.services.EmailService;

@Controller
public class EmailController {
	
	@Autowired
	private UserRepository uRepo;
	
	@Autowired
	private EmailService eService;
	
	@Autowired
	private TaskExecutor taskExecutor;
	
	private static final int BATCH_SIZE = 100; // Adjust batch size as per your needs
	
	
	//@PostMapping("/send")
	@Scheduled(fixedRate =21600000 ) // Run every 6 hours (6 * 60 * 60 * 1000 milliseconds)
    public ResponseEntity<String> sendEmails() {
        try {
        	List<User> users = uRepo.findAll();
            int totalUsers = users.size();

            for (int i = 0; i < totalUsers; i += BATCH_SIZE) {
                int endIndex = Math.min(i + BATCH_SIZE, totalUsers);
                List<User> usersBatch = users.subList(i, endIndex);
                taskExecutor.execute(() -> processEmailBatch(usersBatch));
            }

            return ResponseEntity.ok("Emails sent successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to send emails");
        }
    }
	
	private void processEmailBatch(List<User> users) {
        for (User user : users) {
            String recipientEmail = user.getEmail();
            String subject = "Your Subject";
            String content = "Your Email Body";
            boolean emailSent = eService.sendEmail(recipientEmail, subject, content);
            if (emailSent) {
                CSVLogger.logEmail(recipientEmail);
            }
        }
    }

}

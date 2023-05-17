package com.graphy.models;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVLogger {
	
	private static final String CSV_FILE_PATH = "C:\\Users\\surya\\OneDrive\\Desktop\\mails_log.csv";

    public static void logEmail(String email) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE_PATH, true))) {
            writer.println(email);
        } catch (IOException e) {
            System.out.println("Failed to log email: " + email);
            e.printStackTrace();
        }
    }

}

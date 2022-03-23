package com.techelevator.view;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.techelevator.view.VendingMachine.currencyFormat;

public class Logger {
    String fileInputName = "src/main/resources/log.txt";
    public File logFile = new File(fileInputName);

    public void logToFile(String action, BigDecimal balanceBefore, BigDecimal balanceAfter) {
        String fileInputName = "src/main/resources/log.txt";
        File logFile = new File(fileInputName);
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss a");

        if (!logFile.exists() || !logFile.isFile()) {
            try {
                logFile.createNewFile();
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } else {
            try (PrintWriter writer = new PrintWriter(new FileOutputStream(logFile, true))) {
                writer.println(dateTime.format(format) + " " + action + " " + currencyFormat(balanceBefore) + " "
                        + currencyFormat(balanceAfter));
            } catch (FileNotFoundException fnf) {
                System.out.println(fnf.getMessage());
            }

        }

    }

}


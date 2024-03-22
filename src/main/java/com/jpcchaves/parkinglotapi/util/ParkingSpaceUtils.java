package com.jpcchaves.parkinglotapi.util;

import java.time.LocalDateTime;

public class ParkingSpaceUtils {
    public static String generateReceipt() {
        LocalDateTime date = LocalDateTime.now();
        String receipt = date.toString().substring(0, 19);
        return receipt
                .replace("-", "")
                .replace(":", "")
                .replace("T", "-");
    }

    
}

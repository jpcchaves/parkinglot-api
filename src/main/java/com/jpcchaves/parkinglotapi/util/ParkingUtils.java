package com.jpcchaves.parkinglotapi.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class ParkingUtils {
  private static final double FIRST_15_MINUTES_COST = 5.00;
  private static final double FIRST_60_MINUTES_COST = 9.25;
  private static final double EXTRA_15_MINUTES_COST = 1.75;

  public ParkingUtils() {
  }

  public static BigDecimal calculateCost(LocalDateTime entryDate,
                                         LocalDateTime exitDate) {
    long minutes = entryDate.until(exitDate, ChronoUnit.MINUTES);
    double total;

    if (minutes <= 15) {
      total = FIRST_15_MINUTES_COST;
    } else if (minutes <= 60) {
      total = FIRST_60_MINUTES_COST;
    } else {
      long extraTime = minutes - 60;
      double extraTimeAmount = Math.ceil((float) extraTime / 15);
      total = FIRST_60_MINUTES_COST + (extraTimeAmount * EXTRA_15_MINUTES_COST);
    }

    return new BigDecimal(total).setScale(2, RoundingMode.HALF_EVEN);
  }

}


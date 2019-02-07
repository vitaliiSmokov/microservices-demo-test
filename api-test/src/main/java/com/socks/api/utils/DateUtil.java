package com.socks.api.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author Vitalii Smokov 07.02.2019
 */
public class DateUtil {

  public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant()
        .atZone(ZoneId.systemDefault())
        .toLocalDate();
  }

  public static Date convertToDateViaInstant(LocalDate dateToConvert) {
    return Date.from(dateToConvert.atStartOfDay()
        .atZone(ZoneId.systemDefault())
        .toInstant());
  }

  public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
    return Date
        .from(dateToConvert.atZone(ZoneId.systemDefault())
            .toInstant());
  }
}

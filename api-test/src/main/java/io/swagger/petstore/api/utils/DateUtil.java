package io.swagger.petstore.api.utils;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

@UtilityClass
public class DateUtil {

  public static LocalDate convertToLocalDateViaInstant(Date dateToConvert) {
    return dateToConvert.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
  }

  public static Date convertToDateViaInstant(LocalDate dateToConvert) {
    return Date.from(dateToConvert.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
  }

  public static Date convertToDateViaInstant(LocalDateTime dateToConvert) {
    return Date.from(dateToConvert.atZone(ZoneId.systemDefault()).toInstant());
  }

  public static ZonedDateTime toZonedDateTime(Date date) {
    if (date == null) {
      return null;
    }
    final ZoneId systemDefault = ZoneId.systemDefault();
    return ZonedDateTime.ofInstant(date.toInstant(), systemDefault);
  }
}

package io.swagger.petstore.api.utils;

import lombok.experimental.UtilityClass;

import java.time.*;
import java.time.format.DateTimeFormatter;

@UtilityClass
public class DateTimeUtil {

  private static final String FORMAT = "yyyy-MM-dd'T'HH:mm:ssxxxxx";
  private static final String SIMPLE_PATTERN = "yyyy-MM-dd HH:mm:ss";

  public static String toLocalizedDate(Long time) {
    return Instant.ofEpochMilli(time)
        .atZone(ZoneId.systemDefault())
        .format(DateTimeFormatter.ofPattern(FORMAT));
  }

  public static String toString(LocalDateTime date) {
    return date.format(DateTimeFormatter.ofPattern(SIMPLE_PATTERN));
  }

  public static String toString(LocalDate date) {
    return date.format(DateTimeFormatter.ISO_DATE);
  }

  public static LocalDateTime toLocalDateTime(String date) {
    return LocalDateTime.ofInstant(Instant.parse(date), ZoneOffset.UTC);
  }

  public static LocalDateTime toLocalDateTimeBySimplePattern(String date) {
    return LocalDateTime.parse(date, DateTimeFormatter.ofPattern(SIMPLE_PATTERN));
  }

  public static Instant localDateTimeToInstantUTC(LocalDateTime date) {
    return localDateTimeToInstant(date, ZoneOffset.UTC);
  }

  public static Instant localDateTimeToInstant(LocalDateTime date, ZoneOffset zone) {
    return date.toInstant(zone);
  }

  public static LocalDate toLocalDate(String date) {
    return LocalDate.parse(date, DateTimeFormatter.ISO_DATE);
  }
}

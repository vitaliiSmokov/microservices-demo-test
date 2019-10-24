package io.swagger.petstore.ui.constants;

import java.text.*;
import java.time.format.*;
import java.util.*;

public class FormatConstants {
     public static final DateTimeFormatter ISO_DATE_TIME_FORMATTER = DateTimeFormatter.ISO_DATE_TIME;
    public static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern(DatePatternConstant.DATE_PATTERN, Locale.ENGLISH);
    public static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DatePatternConstant.DATE_TIME_PATTERN, Locale.ENGLISH);
    public static final DateTimeFormatter DATE_TIME_SECONDS_FORMATTER
            = DateTimeFormatter.ofPattern(DatePatternConstant.DATE_TIME_SECONDS_PATTERN, Locale.ENGLISH);

    public static final DateTimeFormatter DATE_TIME_ISO_UTC = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssX");

    public static final DateTimeFormatter DATE_TIME_SECONDS_NO_SPACE_FORMATTER
            = DateTimeFormatter.ofPattern(DatePatternConstant.DATE_TIME_SECONDS_NO_SPACE_PATTERN, Locale.ENGLISH);
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DatePatternConstant.DATE_TIME_SECONDS_PATTERN);
}

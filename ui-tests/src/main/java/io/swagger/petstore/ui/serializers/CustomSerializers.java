package io.swagger.petstore.ui.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.swagger.petstore.ui.constants.DatePatternConstant;
import io.swagger.petstore.ui.constants.FormatConstants;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import static java.time.format.DateTimeFormatter.ofPattern;


public class CustomSerializers {

    public static class LongSerializer extends JsonSerializer<Long> {
        @Override
        public void serialize(Long value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.toString());
        }
    }

    public static class LongDeserializer extends JsonDeserializer<Long> {
        @Override
        public Long deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            return p.getValueAsLong();
        }
    }

    public static class ZonedDateTimeAsPlainDateDeserializer extends
            JsonDeserializer<ZonedDateTime> {
        @Override
        public ZonedDateTime deserialize(
                JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            LocalDate parsedLocalDate = LocalDate.parse(p.getValueAsString(), FormatConstants.DATE_FORMATTER);
            return ZonedDateTime.of(parsedLocalDate.atTime(0, 0), ZoneId.of("UTC"));
        }
    }

    public static class ZonedDateTimeAsPlainDateSerializer extends JsonSerializer<ZonedDateTime> {
        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            LocalDate localDate = value.toLocalDate();
            String dateFormatted = localDate.format(FormatConstants.DATE_FORMATTER);
            gen.writeString(dateFormatted);
        }
    }

    public static class ZonedDateDeserializer extends JsonDeserializer<ZonedDateTime> {
        @Override
        //2001-12-17T00:00:00.000+0000"
        public ZonedDateTime deserialize(
                JsonParser p, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            String valueAsString = p.getValueAsString();
            if (valueAsString.matches("^\\d{4}(-\\d{2}){2}T(\\d{2}:?){3}\\.\\d{3}\\+\\d{4}$")) {
                String[] timeStamp = valueAsString.split("T");
                return ZonedDateTime.parse(timeStamp[0], FormatConstants.DATE_TIME_ISO_UTC);
            } else {
                return ZonedDateTime.parse(valueAsString, FormatConstants.DATE_TIME_ISO_UTC);
            }
        }
    }

    public static class ZonedDateTimeSerializer extends JsonSerializer<ZonedDateTime> {
        @Override
        public void serialize(ZonedDateTime value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
            gen.writeString(value.withZoneSameInstant(ZoneId.of("UTC")).format(FormatConstants.DATE_TIME_ISO_UTC));
        }
    }

    public static class LocalDateTimeSerializer extends JsonSerializer<LocalDateTime> {
        @Override
        public void serialize(LocalDateTime value, JsonGenerator gen,
                              SerializerProvider serializers)
                throws IOException {
            gen.writeString(value.format(ofPattern(DatePatternConstant.DATE_TIME_SECONDS_PATTERN)));
        }
    }
}

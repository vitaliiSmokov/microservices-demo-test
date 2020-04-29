package io.swagger.petstore.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import io.swagger.petstore.api.utils.DateTimeUtil;

import java.io.IOException;
import java.time.LocalDate;

public class DateToStringSerializer extends JsonSerializer<LocalDate> {

    @Override
    public void serialize(LocalDate value, JsonGenerator gen,
            SerializerProvider serializers) throws IOException {
        gen.writeObject(DateTimeUtil.toString(value));
    }
}

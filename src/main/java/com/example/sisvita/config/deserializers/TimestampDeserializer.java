package com.example.sisvita.config.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimestampDeserializer extends JsonDeserializer<Timestamp> {

    private static final SimpleDateFormat dateFormat1;
    private static final SimpleDateFormat dateFormat2;

    static {
        dateFormat1 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
        dateFormat1.setTimeZone(TimeZone.getTimeZone("America/Lima"));

        dateFormat2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        dateFormat2.setTimeZone(TimeZone.getTimeZone("America/Lima"));
    }

    @Override
    public Timestamp deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        try {
            Date parsedDate;
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                parsedDate = dateFormat2.parse(date);
            } else {
                parsedDate = dateFormat1.parse(date);
            }
            return new Timestamp(parsedDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}
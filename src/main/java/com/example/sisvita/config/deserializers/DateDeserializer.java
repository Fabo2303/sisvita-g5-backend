package com.example.sisvita.config.deserializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateDeserializer extends JsonDeserializer<java.sql.Date> {

    private static final SimpleDateFormat dateFormat1 = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
    private static final SimpleDateFormat dateFormat2 = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public java.sql.Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        String date = jsonParser.getText();
        try {
            Date parsedDate;
            if (date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                parsedDate = dateFormat2.parse(date);
            } else {
                parsedDate = dateFormat1.parse(date);
            }
            return new java.sql.Date(parsedDate.getTime());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}

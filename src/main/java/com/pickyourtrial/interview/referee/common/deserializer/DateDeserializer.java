package com.pickyourtrial.interview.referee.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.pickyourtrial.interview.referee.common.utils.DateUtils;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.pickyourtrial.interview.referee.common.utils.DateUtils.getSupportedLocale;


public class DateDeserializer extends JsonDeserializer<Date> {
    public static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy", getSupportedLocale());

    @Override
    public Date deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        return DateUtils.parseDate(p.getValueAsString(), dateFormat);
    }
}
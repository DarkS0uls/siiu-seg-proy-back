package com.myorg.adapter.in.util.constrains;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

@Slf4j
public class ValueOfDatetimeValidator implements ConstraintValidator<ValueOfDatetime, String> {
    private static final String ISO_FORMAT = "yyyy-MM-dd'T'HH:mm:ss";

    @Override
    public boolean isValid(String datetime, ConstraintValidatorContext constraintValidatorContext) {
        if (datetime == null){
            return true;
        }

        log.info("Validating datetime: {}", datetime);
        TimeZone timeZone = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat(ISO_FORMAT);
        dateFormat.setTimeZone(timeZone);

        try {
            dateFormat.parse(datetime);
            return true;
        } catch (Exception exception) {
            log.warn("Datetime format error: {}", datetime);
            return false;
        }

    }
}

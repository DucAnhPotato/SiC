package vn.sic.core.common.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.format.DateTimeFormatter;

public class ValidTimestampValidator implements ConstraintValidator<ValidTimestamp, String> {
    private DateTimeFormatter formatter;

    @Override
    public void initialize(ValidTimestamp annotation) {
        formatter = DateTimeFormatter.ofPattern(annotation.pattern());
    }

    @Override
    public boolean isValid(String timestamp, ConstraintValidatorContext context) {
        try {
            formatter.parse(timestamp);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

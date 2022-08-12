package br.com.lucasfuck.user.core.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class AdulthoodValidator implements ConstraintValidator<Adulthood, LocalDate> {

    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if (localDate == null)
            return false;

        return localDate.until(LocalDate.now()).getYears() >= 18;
    }
}

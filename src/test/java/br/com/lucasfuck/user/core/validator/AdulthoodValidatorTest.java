package br.com.lucasfuck.user.core.validator;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class AdulthoodValidatorTest {

    @Mock
    private ConstraintValidatorContext context;

    @InjectMocks
    private AdulthoodValidator adulthoodValidator;

    @Test
    void testIsValidAdulthood() {
        boolean valid = adulthoodValidator.isValid(LocalDate.now().minusYears(18), context);
        assertTrue(valid);
    }

    @Test
    void testIsInvalidAdulthood() {
        boolean valid = adulthoodValidator.isValid(LocalDate.now().plusDays(1), context);
        assertFalse(valid);
    }
}
package io.github.mokka88.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static io.github.mokka88.validation.ValidationResult.Status.ERROR;
import static io.github.mokka88.validation.ValidationResult.Status.OK;

public class TestUtil {
    public static void assertInvalid(Validator validator) {
        assertEquals(ERROR, validator.validate().getStatus());
    }

    public static void assertValid(Validator validator) {
        assertEquals(OK, validator.validate().getStatus());
    }
}

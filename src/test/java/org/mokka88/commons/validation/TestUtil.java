package org.mokka88.commons.validation;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mokka88.commons.validation.ValidationResult.Status.ERROR;
import static org.mokka88.commons.validation.ValidationResult.Status.OK;

public class TestUtil {
    public static void assertInvalid(Validator validator) {
        assertEquals(ERROR, validator.validate().getStatus());
    }

    public static void assertValid(Validator validator) {
        assertEquals(OK, validator.validate().getStatus());
    }
}

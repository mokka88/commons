package org.mokka88.commons.validation;

import org.junit.jupiter.api.Test;

import static org.mokka88.commons.validation.TestUtil.assertInvalid;
import static org.mokka88.commons.validation.TestUtil.assertValid;

class NoArgsValidatorTest {
    @Test
    void testTrue() {
        assertValid(new NoArgsValidator(() -> true));
    }

    @Test
    void testFalse() {
        assertInvalid(new NoArgsValidator(() -> false));
    }
}
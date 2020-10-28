package io.github.mokka88.validation;

import org.junit.jupiter.api.Test;

import static io.github.mokka88.validation.TestUtil.assertInvalid;
import static io.github.mokka88.validation.TestUtil.assertValid;

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
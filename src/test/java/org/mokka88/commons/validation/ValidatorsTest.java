package org.mokka88.commons.validation;

import org.junit.jupiter.api.Test;

import static org.mokka88.commons.validation.TestUtil.assertInvalid;
import static org.mokka88.commons.validation.TestUtil.assertValid;

class ValidatorsTest {

    @Test
    void testNotEmptyTrue() {
        assertValid(Validators.notEmpty("E!"));
    }

    @Test
    void testNotEmptyFalse() {
        assertInvalid(Validators.notEmpty(null));
        assertInvalid(Validators.notEmpty(""));
    }

    @Test
    void testGreaterThanTrue() {
        assertValid(Validators.greaterThan(2, 1));
        assertValid(Validators.greaterThan("b", "a"));
        assertValid(Validators.greaterThan(null, "a"));
    }

    @Test
    void testGreaterThanFalse() {
        assertInvalid(Validators.greaterThan(0, 1));
        assertInvalid(Validators.greaterThan(1, 1));
        assertInvalid(Validators.greaterThan("b", "b"));
    }

    @Test
    void testLesserThanTrue() {
        assertValid(Validators.lesserThan(2, 3));
        assertValid(Validators.lesserThan("b", "c"));
        assertValid(Validators.lesserThan(null, "a"));
    }

    @Test
    void testLesserThanFalse() {
        assertInvalid(Validators.lesserThan(4, 1));
        assertInvalid(Validators.lesserThan(1, 1));
        assertInvalid(Validators.lesserThan("b", "b"));
    }

    @Test
    void testEqualsToTrue() {
        assertValid(Validators.equalsTo(2, 2));
        assertValid(Validators.equalsTo("b", "b"));
        assertValid(Validators.equalsTo(null, "a"));
    }

    @Test
    void testEqualsToFalse() {
        assertInvalid(Validators.equalsTo(4, 1));
        assertInvalid(Validators.equalsTo(1, 2));
        assertInvalid(Validators.equalsTo("a", "b"));
    }

    @Test
    void testMatchesRegexTrue() {
        assertValid(Validators.matchesRegex("ee22aa", "[a-z]+[0-9]+[a-z]+"));
        assertValid(Validators.equalsTo(null, "a"));
    }

    @Test
    void testMatchesRegex() {
        assertInvalid(Validators.equalsTo("a", "[0-9]"));
    }
}
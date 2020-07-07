package org.mokka88.commons.validation;

import org.junit.jupiter.api.Test;

import static org.mokka88.commons.validation.TestUtil.assertInvalid;
import static org.mokka88.commons.validation.TestUtil.assertValid;

class CoreValidatorsTest {

    @Test
    void testNotEmptyTrue() {
        assertValid(CoreValidators.notEmpty("E!"));
    }

    @Test
    void testNotEmptyFalse() {
        assertInvalid(CoreValidators.notEmpty(null));
        assertInvalid(CoreValidators.notEmpty(""));
    }

    @Test
    void testGreaterThanTrue() {
        assertValid(CoreValidators.greaterThan(2, 1));
        assertValid(CoreValidators.greaterThan("b", "a"));
        assertValid(CoreValidators.greaterThan(null, "a"));
    }

    @Test
    void testGreaterThanFalse() {
        assertInvalid(CoreValidators.greaterThan(0, 1));
        assertInvalid(CoreValidators.greaterThan(1, 1));
        assertInvalid(CoreValidators.greaterThan("b", "b"));
    }

    @Test
    void testLesserThanTrue() {
        assertValid(CoreValidators.lesserThan(2, 3));
        assertValid(CoreValidators.lesserThan("b", "c"));
        assertValid(CoreValidators.lesserThan(null, "a"));
    }

    @Test
    void testLesserThanFalse() {
        assertInvalid(CoreValidators.lesserThan(4, 1));
        assertInvalid(CoreValidators.lesserThan(1, 1));
        assertInvalid(CoreValidators.lesserThan("b", "b"));
    }

    @Test
    void testEqualsToTrue() {
        assertValid(CoreValidators.equalsTo(2, 2));
        assertValid(CoreValidators.equalsTo("b", "b"));
        assertValid(CoreValidators.equalsTo(null, "a"));
    }

    @Test
    void testEqualsToFalse() {
        assertInvalid(CoreValidators.equalsTo(4, 1));
        assertInvalid(CoreValidators.equalsTo(1, 2));
        assertInvalid(CoreValidators.equalsTo("a", "b"));
    }

    @Test
    void testMatchesRegexTrue() {
        assertValid(CoreValidators.matchesRegex("ee22aa", "[a-z]+[0-9]+[a-z]+"));
        assertValid(CoreValidators.equalsTo(null, "a"));
    }

    @Test
    void testMatchesRegex() {
        assertInvalid(CoreValidators.equalsTo("a", "[0-9]"));
    }

    @Test
    void testHasLengthTrue() {
        assertValid(CoreValidators.hasLength("ee", 2));
    }

    @Test
    void testHasLengthFalse() {
        assertInvalid(CoreValidators.hasLength("eee", 2));
    }

    @Test
    void testMinLengthTrue() {
        assertValid(CoreValidators.minLength("ee", 2));
        assertValid(CoreValidators.minLength("ee", 1));
    }

    @Test
    void testMinLengthFalse() {
        assertInvalid(CoreValidators.minLength("eee", 4));
    }

    @Test
    void testMaxLengthTrue() {
        assertValid(CoreValidators.maxLength("ee", 2));
        assertValid(CoreValidators.maxLength("ee", 3));
    }

    @Test
    void testMaxLengthFalse() {
        assertInvalid(CoreValidators.maxLength("eee", 2));
    }
}
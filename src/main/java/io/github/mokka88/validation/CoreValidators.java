package io.github.mokka88.validation;

import java.math.BigDecimal;

/**
 * A factory for creating basic validators
 *
 * @author mokka88
 */
public interface CoreValidators {
    static <T> Validator<T> notEmpty(T value) {
        return new NotEmptyValidator<T>().withValue(value);
    }

    static <T extends Comparable> Validator<T> greaterThan(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) > 0).withValue(value);
    }

    static <T extends Comparable> Validator<T> greaterOrEquals(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) >= 0).withValue(value);
    }

    static <T extends Comparable> Validator<T> lesserThan(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) < 0).withValue(value);
    }

    static <T extends Comparable> Validator<T> lessOrEquals(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) <= 0).withValue(value);
    }

    static <T> Validator<T> equalsTo(T value, T reference) {
        return new LambdaValidator<T>(v -> v.equals(reference)).withValue(value);
    }

    static Validator<String> matchesRegex(String value, String regex) {
        return new LambdaValidator<String>(v -> v.matches(regex)).withValue(value);
    }

    static Validator<String> hasLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() == length).withValue(value);
    }

    static Validator<String> minLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() >= length).withValue(value);
    }

    static Validator<String> maxLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() <= length).withValue(value);
    }

    static Validator not(Validator validator) {
        return validator.negate();
    }

    static Validator<BigDecimal> wholeNumber(BigDecimal value) {
        return new LambdaValidator<BigDecimal>(v -> v.signum() == 0 || v.scale() <= 0 || v.stripTrailingZeros().scale() <= 0).withValue(value);
    }

    static Validator fail() {
        return new NoArgsValidator(() -> false);
    }
}

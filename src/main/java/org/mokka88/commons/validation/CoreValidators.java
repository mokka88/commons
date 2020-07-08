package org.mokka88.commons.validation;

/**
 * A factory for creating basic validators
 *
 * @author mokka88
 */
public class CoreValidators {
    // No need for an instance
    private CoreValidators() {}

    public static <T> Validator<T> notEmpty(T value) {
        return new NotEmptyValidator<T>().withValue(value);
    }

    public static <T extends Comparable> Validator<T> greaterThan(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) > 0).withValue(value);
    }

    public static <T extends Comparable> Validator<T> lesserThan(T value, T reference) {
        return new LambdaValidator<T>(v -> v.compareTo(reference) < 0).withValue(value);
    }

    public static <T> Validator<T> equalsTo(T value, T reference) {
        return new LambdaValidator<T>(v -> v.equals(reference)).withValue(value);
    }

    public static Validator<String> matchesRegex(String value, String regex) {
        return new LambdaValidator<String>(v -> v.matches(regex)).withValue(value);
    }

    public static Validator<String> hasLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() == length).withValue(value);
    }

    public static Validator<String> minLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() >= length).withValue(value);
    }

    public static Validator<String> maxLength(String value, int length) {
        return new LambdaValidator<String>(v -> v.length() <= length).withValue(value);
    }
}

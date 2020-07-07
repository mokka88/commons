package org.mokka88.commons.validation;

public class Validators {
    // No need for an instance
    private Validators() {}

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
}

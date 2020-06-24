package org.mokka88.commons.validation;

public interface Validator<T> {
    Validator<T> withField(String componentName, T value);
    Validator<T> withValue(T value);
    ValidationResult validate();
    void validateAndThrow() throws ValidationException;
    Validator<T> withErrorName(String errorName);
}

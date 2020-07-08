package org.mokka88.commons.validation;

/**
 * A builder pattern for validating user input data (e.g. from a form)
 *
 * @author mokka88
 * @param <T>
 */
public interface Validator<T> {
    /**
     * Adds a component name and a value to be validated.
     * The component name can help identify the input field, whose input was invalid.
     * It can be useful if the error message needs to be displayed right above the input field.
     *
     * @param componentName
     * @param value
     * @return
     */
    Validator<T> withField(String componentName, T value);

    /**
     * Value to be validated.
     *
     * @param value
     * @return
     */
    Validator<T> withValue(T value);

    /**
     * Do validation and retrieve result.
     *
     * @return
     */
    ValidationResult validate();

    /**
     * Do validation and throw exception containing the result.
     *
     * @throws ValidationException
     */
    void validateAndThrow() throws ValidationException;

    /**
     * Define custom error message.
     *
     * @param message
     * @return
     */
    Validator<T> withErrorMessage(String message);

    /**
     * Delivers the exact opposite result of it's normal functionality
     *
     * @return
     */
    Validator<T> negate();
}

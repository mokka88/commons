package org.mokka88.commons.validation;

import java.util.Collection;

import static org.mokka88.commons.validation.ValidationResult.Status.OK;

/**
 * Default, abstract implementation of {@link Validator}
 * The validation logic itself needs to be implemented in a subclass
 *
 * @author mokka88
 * @param <T>
 */
public abstract class AbstractValidator<T> implements Validator<T> {
    protected final ValidationResult result;
    protected boolean skipIfEmpty = true;

    protected T value = null;
    protected String componentName = null;
    protected String errorName = null;

    public AbstractValidator() {
        result = new ValidationResult();
    }

    protected abstract boolean doValidation();

    public Validator withField(String componentName, T value) {
        this.value = value;
        this.componentName = componentName;

        return this;
    }

    public Validator withValue(T value) {
        this.value = value;

        return this;
    }

    public ValidationResult validate() {
        if (skipIfEmpty && isEmpty()) {
            return result;
        }

        if (!doValidation()) {
            result.addError(componentName, errorName);
        }

        return result;
    }

    public void validateAndThrow() throws ValidationException {
        if (validate().getStatus() != OK) {
            throw new ValidationException(result);
        }
    }

    protected String getErrorName() {
        if (errorName == null && componentName == null) {
            return "Invalid value: " + value;
        }

        if (errorName == null) {
            return "Invalid value for component " + componentName + ": " + value;
        }

        return errorName;
    }

    protected boolean isEmpty() {
        return value == null //
                || ((value instanceof String) && ((String) value).isEmpty()) //
                || ((value instanceof Collection) && ((Collection) value).isEmpty());
    }

    public Validator<T> withErrorMessage(String message) {
        this.errorName = message;

        return this;
    }
}

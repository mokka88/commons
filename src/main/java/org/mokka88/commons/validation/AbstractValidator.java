package org.mokka88.commons.validation;

import static org.mokka88.commons.validation.ValidationResult.Status.OK;

public abstract class AbstractValidator<T> implements Validator<T> {
    protected final ValidationResult result;
    protected boolean skipEmptyValues = true;

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
        if (skipEmptyValues && isEmpty(value)) {
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

    protected boolean isEmpty(final T value) {
        return value == null || ((value instanceof String) && ((String) value).isEmpty());
    }

    public Validator<T> withErrorName(String errorName) {
        this.errorName = errorName;

        return this;
    }
}
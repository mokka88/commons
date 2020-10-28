package io.github.mokka88.validation;

/**
 * Validating whether value is empty
 *
 * @author mokka88
 * @param <T>
 */
class NotEmptyValidator<T> extends AbstractValidator<T> {

    public NotEmptyValidator() {
        super();
        skipIfEmpty = false;
    }

    @Override
    protected boolean doValidation() {
        return !isEmpty();
    }
}

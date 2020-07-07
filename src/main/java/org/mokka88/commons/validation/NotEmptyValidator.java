package org.mokka88.commons.validation;

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

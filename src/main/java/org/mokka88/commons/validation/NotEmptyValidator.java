package org.mokka88.commons.validation;

public class NotEmptyValidator<T> extends AbstractValidator<T> {

    @Override
    protected boolean doValidation() {
        if (value == null) {
            return false;
        }

        if (value instanceof String) {
            return ((String) value).isEmpty();
        }

        return true;
    }
}

package org.mokka88.commons.validation;

import java.util.function.Predicate;

public class LambdaValidator<T> extends AbstractValidator<T> {
    private final Predicate<T> predicate;

    public LambdaValidator(Predicate<T> predicate) {
        this.predicate = predicate;
    }

    @Override
    protected boolean doValidation() {
        return predicate.test(value);
    }
}

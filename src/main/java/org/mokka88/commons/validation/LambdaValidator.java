package org.mokka88.commons.validation;

import java.util.function.Predicate;

/**
 * The validation logic must be defined via lambda expression in the contructor.
 * Meant for quickly implementing one-line, simple validations (see {@link CoreValidators}).
 *
 * @author mokka88
 * @param <T>
 */
public class LambdaValidator<T> extends AbstractValidator<T> {
    private final Predicate<T> predicate;

    public LambdaValidator(Predicate<T> predicate) {
        super();
        this.predicate = predicate;
    }

    @Override
    protected boolean doValidation() {
        return predicate.test(value);
    }
}

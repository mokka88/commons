package io.github.mokka88.validation;

/**
 * Validator for arbitrary lambda expressions
 *
 * @author mokka88
 */
public class NoArgsValidator extends AbstractValidator {
    private final NoArgs noArgs;

    public NoArgsValidator(NoArgs noArgs) {
        super();
        skipIfEmpty = false;
        this.noArgs = noArgs;
    }

    @Override
    protected boolean doValidation() {
        return noArgs.isValid();
    }
}

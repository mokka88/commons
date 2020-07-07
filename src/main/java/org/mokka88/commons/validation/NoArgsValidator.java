package org.mokka88.commons.validation;

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

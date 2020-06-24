package org.mokka88.commons.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static org.mokka88.commons.validation.ValidationResult.Status.ERROR;

public class ValidatorGroup<T> extends AbstractValidator<T> {
    private final List<Validator> componentList = new ArrayList<>();

    private boolean hasExplicitValue = false;

    public ValidatorGroup() {
        super();
    }
    
    @Override
    protected final boolean doValidation() {
        initOtherValidators();

        boolean isValid = true;

        for (Validator component : componentList) {
            if (hasExplicitValue) {
                component.withField(componentName, value);
            }

            ValidationResult componentResult = component.validate();
            if (componentResult.getStatus() == ERROR) {
                isValid = false;
            }

            result.addAll(componentResult);
        }

        return isValid;
    }

    protected void initOtherValidators() {
        // This can be overridden is subclasses if needed
    }

    /**
     * With validator.
     *
     * @param validator
     *            the validator
     * @return the aggregate validator
     */
    public ValidatorGroup<T> withValidator(Validator validator) {
        componentList.add(validator);

        return this;
    }

    public ValidatorGroup<T> withValidator(Predicate predicate) {
        return withValidator(new LambdaValidator(predicate));
    }

    public ValidatorGroup<T> withValidator(NoArgs noArgs) {
        return withValidator(new NoArgsValidator(noArgs));
    }

                                           @Override
    public ValidatorGroup withField(String componentName, T value) {
        super.withField(componentName, value);

        hasExplicitValue = true;

        return this;
    }

    public ValidatorGroup<T> withValue(T value) {
        withField(null, value);

        return this;
    }

    @Override
    public ValidationResult validate() {
        doValidation();
        return result;
    }
}

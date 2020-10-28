package io.github.mokka88.validation;

import io.github.mokka88.validation.ValidationResult.Status;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Using the builder pattern for grouping together validators and aggregating results.
 *
 * @param <T>
 * @author mokka88
 */
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
            if (componentResult.getStatus() == Status.ERROR) {
                isValid = false;
            }

            result.addAll(componentResult);
        }

        return isValid;
    }

    protected void initOtherValidators() {
        // This can be overridden is subclasses if needed
    }

    public ValidatorGroup<T> withValidator(Validator validator) {
        componentList.add(validator);

        return this;
    }

    public ValidatorGroup<T> withValidator(Predicate predicate) {
        return withValidator(new LambdaValidator(predicate));
    }

    public ValidatorGroup<T> withValidator(Predicate predicate, String errorMessage) {
        return withValidator(new LambdaValidator(predicate).withErrorMessage(errorMessage));
    }

    public ValidatorGroup<T> withValidator(NoArgs noArgs) {
        return withValidator(new NoArgsValidator(noArgs));
    }

    public ValidatorGroup<T> withValidator(NoArgs noArgs, String errorMessage) {
        return withValidator(new NoArgsValidator(noArgs).withErrorMessage(errorMessage));
    }

    /**
     * Overrides values to be validated in the validators with a single component name-value pair
     *
     * @param componentName
     * @param value
     * @return
     */
    @Override
    public ValidatorGroup withField(String componentName, T value) {
        super.withField(componentName, value);

        hasExplicitValue = true;

        return this;
    }

    /**
     * Overrides values to be validated in the validators with a single value
     *
     * @param value
     * @return
     */
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

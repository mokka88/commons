package io.github.mokka88.validation;

import lombok.Getter;

/**
 * Exception containing {@link ValidationResult}
 *
 * @author mokka88
 */
@Getter
public class ValidationException extends RuntimeException {
    private final ValidationResult result;

    public ValidationException(ValidationResult result) {
        this.result = result;
    }
}

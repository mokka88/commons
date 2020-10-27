package org.mokka88.commons.validation;

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

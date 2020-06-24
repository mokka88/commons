package org.mokka88.commons.validation;

import lombok.Getter;

@Getter
public class ValidationException extends Exception {
    private final ValidationResult result;

    public ValidationException(ValidationResult result) {
        this.result = result;
    }
}

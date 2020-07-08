package org.mokka88.commons.validation;

/**
 * Functional interface for defining arbitrary validation in a lambda expression
 */
@FunctionalInterface
public interface NoArgs {
    boolean isValid();
}

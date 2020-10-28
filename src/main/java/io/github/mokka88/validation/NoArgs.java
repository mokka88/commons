package io.github.mokka88.validation;

/**
 * Functional interface for defining arbitrary validation in a lambda expression
 */
@FunctionalInterface
public interface NoArgs {
    boolean isValid();
}

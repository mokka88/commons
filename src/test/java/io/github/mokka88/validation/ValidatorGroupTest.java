package io.github.mokka88.validation;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static io.github.mokka88.validation.ValidationResult.Status.ERROR;
import static io.github.mokka88.validation.ValidationResult.Status.OK;
import static io.github.mokka88.validation.CoreValidators.*;

class ValidatorGroupTest {
    public static final String EMAIL_PATTERN = "^[\\w!#$%&’*+/=?`{|}~^-]+(?:\\.[\\w!#$%&’*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
    public static final String NAME_EMPTY = "Name can't be empty";
    public static final String EMAIL_INVALID = "Email format is invalid";
    public static final String TOO_YOUNG = "User must be at least 18 years old";
    private InputData validInput;
    private InputData invalidInput;

    @BeforeEach
    void setUp() {
        validInput = InputData.builder() //
                .name("John Doe") //
                .email("exaple@company.com") //
                .dateOfBirth(LocalDate.of(1977, 05, 22)) //
                .build();

        invalidInput = InputData.builder() //
                .name("") //
                .email("exa@ple@company.com") //
                .dateOfBirth(LocalDate.now()) //
                .build();
    }

    private Validator createValidatorGroup(InputData inputData) {
        return new ValidatorGroup() //
                .withValidator(notEmpty(inputData.getName()) //
                        .withErrorMessage(NAME_EMPTY)) //
                .withValidator(matchesRegex(inputData.getEmail(), EMAIL_PATTERN) //
                        .withErrorMessage(EMAIL_INVALID)) //
                .withValidator(lesserThan(inputData.getDateOfBirth(), LocalDate.now().minusYears(18)) //
                        .withErrorMessage(TOO_YOUNG));
    }

    @Test
    void testValid() {
        ValidationResult result = createValidatorGroup(validInput).validate();
        assertEquals(OK, result.getStatus());
        assertTrue(result.getResults().isEmpty());
    }

    @Test
    void testInValid() {
        ValidationResult result = createValidatorGroup(invalidInput).validate();

        assertInvalidResult(result);
    }

    private void assertInvalidResult(ValidationResult result) {
        assertEquals(ERROR, result.getStatus());

        List<ValidationResult.Message> flatResults = result.getMessages();
        assertEquals(3, flatResults.size());

        List<String> messages = result.getMessageTexts();
        assertTrue(messages.contains(NAME_EMPTY));
        assertTrue(messages.contains(EMAIL_INVALID));
        assertTrue(messages.contains(TOO_YOUNG));
    }

    @Test
    void testException() throws ValidationException {
         ValidationException exception = assertThrows(ValidationException.class, () -> createValidatorGroup(invalidInput).validateAndThrow());
         ValidationResult result = exception.getResult();

         assertInvalidResult(result);
    }
}
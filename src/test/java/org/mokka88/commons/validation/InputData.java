package org.mokka88.commons.validation;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Builder
@Getter
public class InputData {
    private String name;
    private String email;
    private LocalDate dateOfBirth;
}

package com.challenge.api.dto;

import jakarta.validation.constraints.*;
import java.time.Instant;

public record EmployeeRequestDTO(
        @NotBlank(message = "Please enter the first name of the employee") String firstName,
        @NotBlank(message = "Please enter the last name of the employee") String lastName,
        String fullName,
        @NotNull(message = "Please enter the salary of the employee") @Positive(message = "The salary of the employee cannot be negative") Integer salary,
        @NotNull(message = "Please enter the age of the employee") @Min(value = 18, message = "The employee should be at least 18 years old")
                Integer age,
        @NotBlank(message = "Please enter the job title of the employee") String jobTitle,
        @NotBlank(message = "Please enter the email of the employee") @Email(message = "The email is not valid")
                String email,
        @NotNull(message = "Please entire the hiring details of the employee") Instant contractHireDate,
        Instant contractTerminationDate) {}

package com.library_management.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotEmpty(message = "First name must not empty")
    private String firstName;
    @NotEmpty(message = "Last name must not empty")
    private String lastName;
    @NotEmpty(message = "Gender must not empty")
    private String gender;
    @NotEmpty(message = "Major must not empty")
    private String major;

}

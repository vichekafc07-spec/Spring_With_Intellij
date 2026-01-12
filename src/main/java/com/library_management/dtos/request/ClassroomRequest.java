package com.library_management.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClassroomRequest {
    @NotEmpty(message = "Name must not empty")
    private String name;
}

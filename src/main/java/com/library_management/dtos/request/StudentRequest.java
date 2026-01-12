package com.library_management.dtos.request;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentRequest {

    private Long studentId;

    private Long classroomId;
}

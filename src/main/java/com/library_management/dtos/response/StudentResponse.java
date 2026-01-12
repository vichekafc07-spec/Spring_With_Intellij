package com.library_management.dtos.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StudentResponse {
    private Long id;
    private Long studentId;
    private String studentName;
    private String gender;
    private String major;
    private Long classroomId;
    private String classroomName;
}

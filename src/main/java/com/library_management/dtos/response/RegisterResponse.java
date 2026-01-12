package com.library_management.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class RegisterResponse {
    private Long studentId;
    private String name;
    private String gender;
    private String major;
    private String idCard;
    private String createdBy;
    private LocalDateTime createdAt;
    private String updatedBy;
}

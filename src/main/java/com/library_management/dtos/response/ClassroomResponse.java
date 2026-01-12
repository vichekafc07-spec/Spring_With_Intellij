package com.library_management.dtos.response;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ClassroomResponse {
    private Long classroom_id;
    private String classroom_name;
    private LocalDateTime createdAt;
    private String createdBy;
}

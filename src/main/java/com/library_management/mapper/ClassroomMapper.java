package com.library_management.mapper;

import com.library_management.dtos.request.ClassroomRequest;
import com.library_management.dtos.response.ClassroomResponse;
import com.library_management.model.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {
    public Classroom toEntity(ClassroomRequest classroomRequest) {
        return Classroom.builder()
                .name(classroomRequest.getName())
                .build();
    }
    public ClassroomResponse toResponse(Classroom classroom) {
        return ClassroomResponse.builder()
                .classroom_id(classroom.getId())
                .classroom_name(classroom.getName())
                .createdBy(classroom.getCreatedBy())
                .createdAt(classroom.getCreatedAt())
                .build();
    }
}

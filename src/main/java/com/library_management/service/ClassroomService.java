package com.library_management.service;

import com.library_management.dtos.request.ClassroomRequest;
import com.library_management.dtos.response.ClassroomResponse;
import com.library_management.model.Classroom;
import jakarta.validation.Valid;

import java.util.List;

public interface ClassroomService {
    ClassroomResponse createClassroom(@Valid ClassroomRequest classroomRequest);

    List<ClassroomResponse> getAllClassroom();

    ClassroomResponse getClassroomById(Long classroomId);

    ClassroomResponse updateClassroom(Long classroomId, @Valid ClassroomRequest classroomRequest);

    ClassroomResponse deleteClassroom(Long classroomId);

    Classroom getById(Long id);
}

package com.library_management.service;

import com.library_management.dtos.request.StudentRequest;
import com.library_management.dtos.response.StudentResponse;
import com.library_management.util.PageResponse;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    StudentResponse createStudent(@Valid StudentRequest studentRequest);

    List<StudentResponse> getAllStudent();

    PageResponse<StudentResponse> filter(Long id, Long classroomId, String sortBy, String sortAs, Integer pageNumber, Integer pageSize);

    StudentResponse updateStudent(Long id, StudentRequest request);

    StudentResponse deleteStudent(Long id);
}

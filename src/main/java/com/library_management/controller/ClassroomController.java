package com.library_management.controller;

import com.library_management.dtos.request.ClassroomRequest;
import com.library_management.dtos.response.ClassroomResponse;
import com.library_management.exceptions.APIResponse;
import com.library_management.service.ClassroomService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classrooms")
@RequiredArgsConstructor
public class ClassroomController {
    private final ClassroomService classroomService;

    @PostMapping
    public ResponseEntity<APIResponse<ClassroomResponse>> addClassroom(@Valid @RequestBody ClassroomRequest classroomRequest) {
        ClassroomResponse response = classroomService.createClassroom(classroomRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.create(response,"Classroom created successfully"));
    }
    @GetMapping
    public ResponseEntity<APIResponse<List<ClassroomResponse>>> getAllClassrooms() {
        List<ClassroomResponse> response = classroomService.getAllClassroom();
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response));
    }
    @GetMapping("/{classroomId}")
    public ResponseEntity<APIResponse<ClassroomResponse>> getClassroomById(@PathVariable Long classroomId) {
        ClassroomResponse response = classroomService.getClassroomById(classroomId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response));
    }

    @PutMapping("/{classroomId}")
    public ResponseEntity<APIResponse<ClassroomResponse>> updateClassrooms(@Valid @RequestBody ClassroomRequest classroomRequest,
                                                                          @PathVariable Long classroomId) {
        ClassroomResponse response = classroomService.updateClassroom(classroomId,classroomRequest);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response));
    }

    @DeleteMapping("/{classroomId}")
    public ResponseEntity<APIResponse<ClassroomResponse>> deleteClassrooms(@PathVariable Long classroomId) {
        ClassroomResponse response = classroomService.deleteClassroom(classroomId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response));
    }

}

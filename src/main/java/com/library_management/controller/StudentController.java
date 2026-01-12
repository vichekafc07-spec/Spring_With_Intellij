package com.library_management.controller;

import com.library_management.dtos.request.StudentRequest;
import com.library_management.dtos.response.StudentResponse;
import com.library_management.exceptions.APIResponse;
import com.library_management.service.StudentService;
import com.library_management.util.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentController {
    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<APIResponse<StudentResponse>> createStudents(@Valid @RequestBody StudentRequest studentRequest){
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.create(studentService.createStudent(studentRequest),"Student create successfully!"));
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<StudentResponse>>> getAllStudents(){
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(studentService.getAllStudent()));
    }

    @GetMapping("/filters")
    public ResponseEntity<APIResponse<PageResponse<StudentResponse>>> filters(@RequestParam(required = false) Long id,
                                                                              @RequestParam(required = false) Long classroomId,
                                                                              @RequestParam(required = false) String sortBy,
                                                                              @RequestParam(required = false) String sortAs,
                                                                              @RequestParam(required = false,defaultValue = "1") Integer pageNumber,
                                                                              @RequestParam(required = false,defaultValue = "5") Integer pageSize){
        PageResponse<StudentResponse> response = studentService.filter(id,classroomId,sortBy,sortAs,pageNumber,pageSize);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response,"Filter successfully !"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> updatedStudents(@PathVariable Long id,
                                                                       @RequestBody StudentRequest request){
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(studentService.updateStudent(id,request),"Updated Successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<StudentResponse>> deletedStudents(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(studentService.deleteStudent(id),"Deleted Successfully"));
    }

}

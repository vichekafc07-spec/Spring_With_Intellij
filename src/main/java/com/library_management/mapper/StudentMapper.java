package com.library_management.mapper;

import com.library_management.dtos.request.StudentRequest;
import com.library_management.dtos.response.StudentResponse;
import com.library_management.model.Classroom;
import com.library_management.model.Register;
import com.library_management.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {
    public Student toEntity(StudentRequest request, Register register, Classroom classroom){
        return Student.builder()
                .classrooms(classroom)
                .register(register)
                .build();
    }
    public StudentResponse toResponse(Student student){
        return StudentResponse.builder()
                .id(student.getId())
                .studentId(student.getRegister().getId())
                .studentName(student.getRegister().getFullName())
                .gender(student.getRegister().getGender())
                .major(student.getRegister().getMajor())
                .classroomId(student.getClassrooms().getId())
                .classroomName(student.getClassrooms().getName())
                .build();
    }
}

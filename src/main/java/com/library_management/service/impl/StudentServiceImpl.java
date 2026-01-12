package com.library_management.service.impl;

import com.library_management.dtos.request.StudentRequest;
import com.library_management.dtos.response.StudentResponse;
import com.library_management.exceptions.ResourceNotFoundException;
import com.library_management.mapper.StudentMapper;
import com.library_management.model.Classroom;
import com.library_management.model.Register;
import com.library_management.model.Student;
import com.library_management.repository.StudentRepository;
import com.library_management.service.ClassroomService;
import com.library_management.service.RegisterService;
import com.library_management.service.StudentService;
import com.library_management.util.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    private final RegisterService registerService;
    private final ClassroomService classroomService;

    @Override
    public StudentResponse createStudent(StudentRequest studentRequest) {
        Classroom classroom = classroomService.getById(studentRequest.getClassroomId());
        Register register = registerService.getById(studentRequest.getStudentId());

        Student student = studentMapper.toEntity(studentRequest,register,classroom);
        Student saveStudent = studentRepository.save(student);

        return studentMapper.toResponse(saveStudent);
    }

    @Override
    public List<StudentResponse> getAllStudent() {
        List<Student> students = studentRepository.findAll();
        return students.stream()
                .map(studentMapper::toResponse)
                .toList();
    }

    @Override
    public PageResponse<StudentResponse> filter(Long id, Long classroomId, String sortBy, String sortAs, Integer pageNumber, Integer pageSize) {
        Specification<Student> specification = Specification.unrestricted();
        if (id != null){
            specification = specification.and(((root, query, cb) ->
                    cb.equal(root.get("id"),id)));
        }
        if (classroomId != null){
            specification = specification.and(((root, query, cb) ->
                    cb.equal(root.get("classrooms").get("id"),classroomId)));
        }

        List<String> allowSort = List.of("classroomId","id");
        Sort sort = Sort.by(Sort.Order.desc("id"));
        if (sortBy != null && sortAs != null && allowSort.contains(sortBy)){
            sort = sortAs.equalsIgnoreCase("desc") ?
                    Sort.by(Sort.Order.desc(sortBy)) :
                    Sort.by(Sort.Order.asc(sortBy));
        }
        Pageable pageable = PageRequest.of(pageNumber -1,pageSize, sort);
        Page<Student> students = studentRepository.findAll(specification,pageable);

        return PageResponse.from(students , studentMapper::toResponse);
    }

    @Override
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        Student student = getById(id);
        Classroom classroom = classroomService.getById(request.getClassroomId());
        Register register = registerService.getById(request.getStudentId());
        student.setClassrooms(classroom);
        student.setRegister(register);
        Student updatedStudent = studentRepository.save(student);
        return studentMapper.toResponse(updatedStudent);
    }

    @Override
    public StudentResponse deleteStudent(Long id) {
        Student student = getById(id);
        studentRepository.delete(student);
        return studentMapper.toResponse(student);
    }

    public Student getById(Long id){
        return studentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student","student id", id));
    }

}
package com.library_management.service.impl;

import com.library_management.dtos.request.ClassroomRequest;
import com.library_management.dtos.response.ClassroomResponse;
import com.library_management.exceptions.APIException;
import com.library_management.exceptions.ResourceNotFoundException;
import com.library_management.mapper.ClassroomMapper;
import com.library_management.model.Classroom;
import com.library_management.repository.ClassroomRepository;
import com.library_management.service.ClassroomService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClassroomServiceImpl implements ClassroomService {
    private final ClassroomRepository classroomRepository;
    private final ClassroomMapper classroomMapper;

    @Override
    public ClassroomResponse createClassroom(ClassroomRequest classroomRequest) {
        Classroom classroom = classroomMapper.toEntity(classroomRequest);
        Classroom classroomFromDb = classroomRepository.findClassroomsByName(classroom.getName());
        if (classroomFromDb != null) {
            throw new APIException("Classroom with name " + classroom.getName() + " already exists");
        }

        return classroomMapper.toResponse(classroomRepository.save(classroom));
    }

    @Override
    public List<ClassroomResponse> getAllClassroom() {
        List<Classroom> classrooms = classroomRepository.findAll();
        return classrooms.stream()
                .map(classroomMapper::toResponse)
                .toList();
    }

    @Override
    public ClassroomResponse getClassroomById(Long classroomId) {
        Classroom classroom = getById(classroomId);
        return classroomMapper.toResponse(classroom);
    }

    @Override
    public ClassroomResponse updateClassroom(Long classroomId, ClassroomRequest classroomRequest) {
        Classroom classroom = getById(classroomId);
        classroom.setName(classroomRequest.getName());
        return classroomMapper.toResponse(classroomRepository.save(classroom));
    }

    @Override
    public ClassroomResponse deleteClassroom(Long classroomId) {
        Classroom classroom = getById(classroomId);
        classroomRepository.delete(classroom);
        return classroomMapper.toResponse(classroom);
    }

    @Override
    public Classroom getById(Long classroomId) {
        return classroomRepository.findById(classroomId)
                .orElseThrow(()-> new ResourceNotFoundException("Classroom","classroom Id", classroomId));
    }
}

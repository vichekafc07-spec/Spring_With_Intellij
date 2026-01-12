package com.library_management.repository;

import com.library_management.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    Classroom findClassroomsByName(String name);
}

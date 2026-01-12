package com.library_management.repository;

import com.library_management.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface StudentRepository extends JpaRepository<Student,Long> , JpaSpecificationExecutor<Student> {
}

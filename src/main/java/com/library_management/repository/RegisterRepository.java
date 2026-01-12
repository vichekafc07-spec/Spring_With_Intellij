package com.library_management.repository;

import com.library_management.model.Register;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface RegisterRepository extends JpaRepository<Register, Long>, JpaSpecificationExecutor<Register> {
}

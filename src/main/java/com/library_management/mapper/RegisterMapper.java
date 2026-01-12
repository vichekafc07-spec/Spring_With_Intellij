package com.library_management.mapper;

import com.library_management.dtos.request.RegisterRequest;
import com.library_management.dtos.response.RegisterResponse;
import com.library_management.model.Register;
import org.springframework.stereotype.Component;

@Component
public class RegisterMapper {
    public Register toEntity(RegisterRequest registerRequest) {
        return Register.builder()
                .firstName(registerRequest.getFirstName())
                .lastName(registerRequest.getLastName())
                .gender(registerRequest.getGender())
                .major(registerRequest.getMajor())
                .build();
    }

    public RegisterResponse toResponse(Register register) {
        return RegisterResponse.builder()
                .studentId(register.getId())
                .name(register.getFullName())
                .gender(register.getGender())
                .major(register.getMajor())
                .idCard(register.getIdCard())
                .createdBy(register.getCreatedBy())
                .createdAt(register.getCreatedAt())
                .updatedBy(register.getUpdatedBy())
                .build();
    }

}

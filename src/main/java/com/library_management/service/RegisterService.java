package com.library_management.service;

import com.library_management.dtos.request.RegisterRequest;
import com.library_management.dtos.response.RegisterResponse;
import com.library_management.model.Register;
import com.library_management.util.PageResponse;
import jakarta.validation.Valid;

import java.time.LocalDateTime;
import java.util.List;

public interface RegisterService {
    RegisterResponse registerStudent(RegisterRequest registerRequest);

    List<RegisterResponse> getAllRegister();

    RegisterResponse getRegisterById(Long id);

    PageResponse<RegisterResponse> filter(Long id, String name, String gender, String major, String sortBy, String sortAs, Integer pageNumber, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate);

    RegisterResponse updateRegister(@Valid RegisterRequest registerRequest, Long id);

    RegisterResponse deleteRegister(Long id);

    Register getById(Long id);
}

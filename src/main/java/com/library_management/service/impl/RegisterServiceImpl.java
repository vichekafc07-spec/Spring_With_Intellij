package com.library_management.service.impl;

import com.library_management.dtos.request.RegisterRequest;
import com.library_management.dtos.response.RegisterResponse;
import com.library_management.exceptions.ResourceNotFoundException;
import com.library_management.mapper.RegisterMapper;
import com.library_management.model.Register;
import com.library_management.repository.RegisterRepository;
import com.library_management.service.RegisterService;
import com.library_management.util.PageResponse;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegisterServiceImpl implements RegisterService {
    private final RegisterRepository registerRepository;
    private final RegisterMapper registerMapper;

    @Override
    @Transactional
    public RegisterResponse registerStudent(RegisterRequest registerRequest) {
        Register register = registerMapper.toEntity(registerRequest);
        register.setFullName(registerRequest.getFirstName() + " " + registerRequest.getLastName());

        Register saveRegister = registerRepository.save(register);

        String idCard = String.format("%03d-UME", register.getId());
        saveRegister.setIdCard(idCard);
        registerRepository.save(saveRegister);

        return registerMapper.toResponse(registerRepository.save(register));
    }

    @Override
    public List<RegisterResponse> getAllRegister() {
        List<Register> registers = registerRepository.findAll();
        return registers.stream()
                .map(registerMapper::toResponse)
                .toList();
    }

    @Override
    public RegisterResponse getRegisterById(Long id) {
        Register register = getById(id);
        return registerMapper.toResponse(register);
    }

    @Override
    public PageResponse<RegisterResponse> filter(Long id, String name, String gender, String major, String sortBy, String sortAs, Integer pageNumber, Integer pageSize, LocalDateTime startDate, LocalDateTime endDate) {
        Specification<Register> specification = Specification.unrestricted();
        if (id != null) {
            specification = specification.and(((root, query, cb) ->
                    cb.equal(root.get("id"), id) ));
        }
        if (name != null) {
            specification = specification.and(((root, query, cb) ->
                    cb.like(cb.upper(root.get("fullName")), "%" + name.toUpperCase() + "%")));
        }
        if (gender != null) {
            specification = specification.and(((root, query, cb) ->
                    cb.equal(root.get("gender"), gender) ));
        }
        if (major != null) {
            specification = specification.and(((root, query, cb) ->
                    cb.equal(root.get("major"), major)));
        }
        if (startDate != null && endDate != null) {
            specification = specification.and(((root, query, cb) ->
                    cb.between(root.get("createdAt"), startDate, endDate) ));
        }

        List<String> allowSort = List.of("id","fullName","gender","major","createdAt");
        Sort sort = Sort.by(Sort.Order.desc("id"));
        if (sortBy != null && sortAs != null && allowSort.contains(sortBy)) {
            sort = sortAs.equalsIgnoreCase("desc") ?
                    Sort.by(Sort.Order.desc(sortBy)) :
                    Sort.by(Sort.Order.asc(sortBy));
        }
        Pageable pageable = PageRequest.of(pageNumber-1, pageSize, sort);
        Page<Register> registerPage = registerRepository.findAll(specification, pageable);

        return PageResponse.from(registerPage, registerMapper::toResponse);
    }

    @Override
    public RegisterResponse updateRegister(RegisterRequest registerRequest, Long id) {
        Register register = getById(id);
        register.setFirstName(registerRequest.getFirstName());
        register.setLastName(registerRequest.getLastName());
        register.setGender(registerRequest.getGender());
        register.setMajor(registerRequest.getMajor());
        register.setCreatedAt(LocalDateTime.now());

        return registerMapper.toResponse(registerRepository.save(register));
    }

    @Override
    public RegisterResponse deleteRegister(Long id) {
        Register register = getById(id);
        registerRepository.delete(register);
        return registerMapper.toResponse(register);
    }

    @Override
    public Register getById(Long id){
        return registerRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Register","register Id",id));
    }
}

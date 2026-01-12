package com.library_management.controller;

import com.library_management.dtos.request.RegisterRequest;
import com.library_management.dtos.response.RegisterResponse;
import com.library_management.exceptions.APIResponse;
import com.library_management.service.RegisterService;
import com.library_management.util.PageResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/register")
@RequiredArgsConstructor
public class RegisterController {
    private final RegisterService registerService;

    @PostMapping
    public ResponseEntity<APIResponse<RegisterResponse>> register( @Valid @RequestBody RegisterRequest registerRequest) {
        RegisterResponse response = registerService.registerStudent(registerRequest);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(APIResponse.create(response,"Student registered successfully!"));
    }

    @GetMapping("/filter")
    public ResponseEntity<PageResponse<RegisterResponse>> filters(@RequestParam(required = false) Long id,
                                                                  @RequestParam(required = false) String name,
                                                                  @RequestParam(required = false) String gender,
                                                                  @RequestParam(required = false) String major,
                                                                  @RequestParam(required = false) String sortBy,
                                                                  @RequestParam(required = false) String sortAs,
                                                                  @RequestParam(required = false,defaultValue = "1") Integer pageNumber,
                                                                  @RequestParam(required = false,defaultValue = "5") Integer pageSize,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startDate,
                                                                  @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endDate) {
        PageResponse<RegisterResponse> pageResponse = registerService.filter(id,name,gender,major,sortBy,sortAs,pageNumber,pageSize,startDate,endDate);
        return ResponseEntity.status(HttpStatus.OK)
                .body(pageResponse);
    }

    @GetMapping
    public ResponseEntity<APIResponse<List<RegisterResponse>>> getAllRegisters(){
        List<RegisterResponse> response = registerService.getAllRegister();
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response,"Get Successfully!"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse<RegisterResponse>> getRegistersById(@PathVariable Long id){
        RegisterResponse response = registerService.getRegisterById(id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response,"Get Successfully!"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse<RegisterResponse>> updateRegisters(@Valid @RequestBody RegisterRequest registerRequest,
                                                                        @PathVariable Long id) {
        RegisterResponse response = registerService.updateRegister(registerRequest,id);
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(response,"Update Successfully!"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse<RegisterResponse>> deleteRegisters(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .body(APIResponse.ok(registerService.deleteRegister(id)));
    }

}

package com.cursos.springsecurity.auth.auth.controller;

import com.cursos.springsecurity.auth.auth.dto.AuthCustomerResponse;
import com.cursos.springsecurity.auth.auth.dto.AuthenticationRequestDto;
import com.cursos.springsecurity.auth.auth.dto.LoginCustomerResponseDto;
import com.cursos.springsecurity.auth.auth.dto.MfaRequest;
import com.cursos.springsecurity.auth.auth.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(description = "Login the user")
    @ApiResponse(responseCode = "200", description = "sucess")
    @PostMapping("/login")
    public ResponseEntity<LoginCustomerResponseDto> login(@Valid @RequestBody AuthenticationRequestDto request){
        LoginCustomerResponseDto response = authService.loginCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Mfa")
    @ApiResponse(responseCode = "200", description = "sucess")
    @PostMapping("/mfa")
    public ResponseEntity<AuthCustomerResponse> mfa(@Valid @RequestBody MfaRequest request){
        return  new ResponseEntity<>(authService.mfaAuthenticationUser(request) , HttpStatus.OK);
    }

}

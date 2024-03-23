package com.cursos.springsecurity.auth.auth.controller;

import com.cursos.springsecurity.auth.auth.dto.*;
import com.cursos.springsecurity.auth.auth.service.AuthService;
import com.cursos.springsecurity.auth.user.service.RefreshTokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    private final RefreshTokenService refreshTokenService;

    public AuthController(AuthService authService, RefreshTokenService refreshTokenService) {
        this.authService = authService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    @Operation(description = "Login the user")
    @ApiResponse(responseCode = "200", description = "sucess")
    public ResponseEntity<LoginCustomerResponseDto> login(@Valid @RequestBody AuthenticationRequestDto request){
        LoginCustomerResponseDto response = authService.loginCustomer(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/mfa")
    @Operation(description = "Mfa")
    @ApiResponse(responseCode = "200", description = "sucess")
    public ResponseEntity<AuthCustomerResponse> mfa(@Valid @RequestBody MfaRequest request){
        return  new ResponseEntity<>(authService.mfaAuthenticationUser(request) , HttpStatus.OK);
    }

    @PostMapping("refresh_token")
    @Operation(description = "Refresh token")
    @ApiResponse(responseCode = "200", description = "sucess")
    public ResponseEntity<RefreshTokenResponse> refreshToken(@RequestBody RefreshTokenRequest request){
        return new ResponseEntity<>(refreshTokenService.refreshToken(request), HttpStatus.OK);
    }

    @GetMapping("/")
    @Operation(description = "login social")
    @ApiResponse(responseCode = "200", description = "sucess")
    public ResponseEntity<Object> loginSocial(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return ResponseEntity.ok(authentication.getPrincipal());
    }

}

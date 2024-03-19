package com.cursos.springsecurity.auth.auth.service;

import com.cursos.springsecurity.auth.auth.dto.AuthCustomerResponse;
import com.cursos.springsecurity.auth.auth.dto.AuthenticationRequestDto;
import com.cursos.springsecurity.auth.auth.dto.LoginCustomerResponseDto;
import com.cursos.springsecurity.auth.auth.dto.MfaRequest;

public interface AuthService {

    LoginCustomerResponseDto loginCustomer(AuthenticationRequestDto request);

    String generateToken(String userName);

    AuthCustomerResponse mfaAuthenticationUser(MfaRequest request);
}

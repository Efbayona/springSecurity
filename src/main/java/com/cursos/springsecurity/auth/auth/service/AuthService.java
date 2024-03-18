package com.cursos.springsecurity.auth.auth.service;

import com.cursos.springsecurity.auth.auth.dto.AuthenticationRequestDto;
import com.cursos.springsecurity.auth.auth.dto.LoginCustomerResponseDto;

public interface AuthService {

    LoginCustomerResponseDto loginCustomer(AuthenticationRequestDto request);

    String generateToken(String userName);
}

package com.cursos.springsecurity.auth.auth.service;

import com.cursos.springsecurity.auth.auth.dto.*;

public interface AuthService {

    LoginCustomerResponseDto loginCustomer(AuthenticationRequestDto request);

    AuthLoginSocialResponse loginSocial(AuthLoginSocialRequest request);

    String generateToken(String userName);

    AuthCustomerResponse mfaAuthenticationUser(MfaRequest request);
}

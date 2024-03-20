package com.cursos.springsecurity.auth.user.service;

import com.cursos.springsecurity.auth.auth.dto.RefreshTokenRequest;
import com.cursos.springsecurity.auth.auth.dto.RefreshTokenResponse;
import com.cursos.springsecurity.auth.user.entity.RefreshToken;
import com.cursos.springsecurity.auth.user.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface RefreshTokenService {

    RefreshTokenResponse refreshToken(RefreshTokenRequest token);

    RefreshToken createRefreshToken(User user);
}

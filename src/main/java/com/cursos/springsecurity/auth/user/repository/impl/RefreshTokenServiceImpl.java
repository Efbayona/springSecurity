package com.cursos.springsecurity.auth.user.repository.impl;

import com.cursos.springsecurity.auth.auth.dto.RefreshTokenRequest;
import com.cursos.springsecurity.auth.auth.dto.RefreshTokenResponse;
import com.cursos.springsecurity.auth.auth.security.jwt.JwtTokenProvider;
import com.cursos.springsecurity.auth.user.entity.RefreshToken;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.common.exception_handler.AccessDeniedException;
import com.cursos.springsecurity.auth.user.repository.RefreshTokenRepository;
import com.cursos.springsecurity.auth.user.service.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final JwtTokenProvider jwtTokenProvider;
    @Value("${settings.auth.token-time}")
    private Integer tokenRefreshTime;

    public RefreshTokenServiceImpl(RefreshTokenRepository refreshTokenRepository, JwtTokenProvider jwtTokenProvider) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public RefreshToken createRefreshToken(User user){
        RefreshToken refreshToken = RefreshToken.create(user);
        return refreshTokenRepository.save(refreshToken);
    }

    private RefreshToken verifyExpiration(String refreshToken) {
        return refreshTokenRepository.findByToken(refreshToken)
                .map(token -> {
                    Duration duration = Duration.between(token.getExpired(), LocalDateTime.now());
                    if (duration.toMinutes() >= tokenRefreshTime){
                        throw  new AccessDeniedException("Token expired");
                    }
                    return token;
                }).orElseThrow(()-> new AccessDeniedException("el token no se encuentra registrado"));
    }

    public RefreshTokenResponse refreshToken(RefreshTokenRequest token) {
        RefreshToken refreshToken = this.verifyExpiration(token.getRefreshTokenId());
        User user = refreshToken.getUser();
        return RefreshTokenResponse.create(this.createRefreshToken(user).getToken(), jwtTokenProvider.generateToken(user.getUserName()));
    }
}

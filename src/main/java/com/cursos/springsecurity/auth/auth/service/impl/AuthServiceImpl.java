package com.cursos.springsecurity.auth.auth.service.impl;

import com.cursos.springsecurity.auth.auth.dto.*;
import com.cursos.springsecurity.auth.auth.exception.AuthenticationFailedException;
import com.cursos.springsecurity.auth.auth.security.jwt.JwtTokenProvider;
import com.cursos.springsecurity.auth.auth.service.AuthService;
import com.cursos.springsecurity.auth.user.entity.RefreshToken;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.auth.user.repository.UserRepository;
import com.cursos.springsecurity.auth.user.service.RefreshTokenService;
import com.cursos.springsecurity.common.util.UtilString;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService;

    @Value("${security.oauth2.client.registration.google.client_id}")
    private String googleClientId;

    public AuthServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    @Override
    public LoginCustomerResponseDto loginCustomer(AuthenticationRequestDto request) {

        User user = userRepository.getUserByUserName(request.getUserName().toLowerCase(Locale.ROOT));

        if (!passwordEncoder.matches(request.getUserPassword(), user.getUserPassword())) {
            throw new AuthenticationFailedException("Las contraseñas no coinciden");
        }

        return LoginCustomerResponseDto.create(user.getUserId(),  UtilString.emailConvertMask(user.getUserEmail()));
    }

    @Override
    public AuthLoginSocialResponse loginSocial(AuthLoginSocialRequest request) {

        GoogleIdTokenVerifier googleIdTokenVerifier = new GoogleIdTokenVerifier.Builder(
                new NetHttpTransport(), new GsonFactory())
                .setAudience(Collections.singletonList(googleClientId))
                .build();

        GoogleIdToken googleIdToken = googleIdTokenVerifier.verify();

        GoogleIdToken.Payload payload = googleIdToken.getPayload();

    }


    @Override
    public AuthCustomerResponse mfaAuthenticationUser(MfaRequest request){

        User user = userRepository.findById(request.getUserId()).orElseThrow(() -> new AuthenticationFailedException("Tenemos problemas, reintenta mas tarde..."));

        String accessToken = generateToken(user.getUserName());
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(user);
        TokenResponse tokenResponse = TokenResponse.create(accessToken , refreshToken.getToken());
        UserCustomerResponse userCustomerResponse = getUserCustomerResponse(user);

        userRepository.save(user);

        return AuthCustomerResponse.create(tokenResponse, userCustomerResponse, userRepository.findModulesByUser(user.getUserId()), userRepository.findPermissionsByUserName(user.getUserName()));
    }

    private static UserCustomerResponse getUserCustomerResponse(User user) {
        return UserCustomerResponse.create(
                user.getUserId(),
                user.getUserName(),
                String.valueOf(user.getUserStatus())
        );
    }


    @Override
    public String generateToken(String userName) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenProvider.generateToken(userDetails.getUsername());
    }

    public User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userRepository.getUserByUserName(userDetails.getUsername());
        }
        return null;
    }

}

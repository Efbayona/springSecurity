package com.cursos.springsecurity.auth.auth.service.impl;

import com.cursos.springsecurity.auth.auth.dto.AuthenticationRequestDto;
import com.cursos.springsecurity.auth.auth.dto.LoginCustomerResponseDto;
import com.cursos.springsecurity.auth.auth.exception.AuthenticationFailedException;
import com.cursos.springsecurity.auth.auth.security.jwt.JwtTokenProvider;
import com.cursos.springsecurity.auth.auth.service.AuthService;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.auth.user.repository.UserRepository;
import com.cursos.springsecurity.common.util.UtilString;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(UserRepository userRepository, UserDetailsService userDetailsService, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.userRepository = userRepository;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public LoginCustomerResponseDto loginCustomer(AuthenticationRequestDto request) {

        User user = userRepository.getUserByName(request.getUserName().toLowerCase(Locale.ROOT));

        if (!passwordEncoder.matches(request.getUserPassword(), user.getPassword())) {
            throw new AuthenticationFailedException("Las contraseñas no coinciden");
        }

        return LoginCustomerResponseDto.create(user.getUserId(),  UtilString.emailConvertMask(user.getEmail()));

    }

    @Override
    public String generateToken(String userName) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return jwtTokenProvider.generateToken(userDetails.getUsername());
    }

    public User getUserAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetails userDetails) {
            return userRepository.getUserByName(userDetails.getUsername());
        }
        return null;
    }

}

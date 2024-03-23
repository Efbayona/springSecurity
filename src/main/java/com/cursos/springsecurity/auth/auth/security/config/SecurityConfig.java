package com.cursos.springsecurity.auth.auth.security.config;

import com.cursos.springsecurity.auth.auth.security.jwt.JwtRequestFilter;
import com.cursos.springsecurity.auth.auth.security.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    private final CustomUserDetailsService customUserDetailsService;
    private final JwtRequestFilter jwtRequestFilter;


    private final String[] ROUTES_ALLOWED_WITHOUT_AUTHENTICATION = {
    };

    private final String[] ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/auth/public-access",
            "/auth/login/social/",
            "/auth/"
    };

    private final String[] ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION = {
            "/auth/mfa",
            "/auth/login",
            "/auth/refresh_token"
    };

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtRequestFilter jwtRequestFilter) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        httpSecurity
                .csrf(Customizer.withDefaults())
                .sessionManagement(sessionManagementConfig -> sessionManagementConfig.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .authenticationProvider(this.authenticationProvider())
                .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> authConfig
                        .requestMatchers(ROUTES_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                        .requestMatchers(HttpMethod.GET, ROUTES_GET_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                        .requestMatchers(HttpMethod.POST, ROUTES_POST_ALLOWED_WITHOUT_AUTHENTICATION).permitAll()
                        .anyRequest().authenticated())
                .oauth2Login(oauth2 -> oauth2
                        .loginPage("/auth/")
                        .defaultSuccessUrl("/auth/", true));
        return httpSecurity.build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(customUserDetailsService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}

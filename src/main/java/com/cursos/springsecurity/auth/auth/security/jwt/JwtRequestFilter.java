package com.cursos.springsecurity.auth.auth.security.jwt;

import com.cursos.springsecurity.auth.auth.security.service.CustomUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    private final JwtTokenProvider jwtTokenProvider;
    private final CustomUserDetailsService customUserDetailsService;

    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider, CustomUserDetailsService customUserDetailsService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //1. Obtener el header que contiene el jwt
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION); // Bearer jwt

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //2. Obtener jwt desde el header
        String jwt = authHeader.split(" ")[1];

        try {

            //3. Obtener el subject/username desde el jwt
            String userName = jwtTokenProvider.extractUsername(jwt);

            //4. Setear un objeto Authentication dentro del SecurityContext
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(userName);

            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(authToken);

        } catch (Exception e){
            logger.error("Cannot set user authentication : {}", e);
        }

        //5. Ejecutar el resto de filtros

        filterChain.doFilter(request, response);
    }
}

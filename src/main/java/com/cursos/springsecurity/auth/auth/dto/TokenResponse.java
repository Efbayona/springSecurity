package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Respuesta del token de acceso.
 */
public class TokenResponse {

    @JsonProperty(value = "token_type")
    private String tokenType = "Bearer";

    @JsonProperty(value = "access_token")
    private String accessToken;

    @JsonProperty(value = "refresh_token")
    private String refreshToken;

    public TokenResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public static TokenResponse create(String accessToken, String refreshToken) {
        return new TokenResponse(accessToken, refreshToken);
    }
}

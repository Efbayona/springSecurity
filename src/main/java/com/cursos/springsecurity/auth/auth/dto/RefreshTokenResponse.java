package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RefreshTokenResponse {

    @JsonProperty(value = "refresh_token",required = true)
    private String refreshToken;

    @JsonProperty(value = "access_token",required = true)
    private String accessToken;

    @JsonProperty(value = "token_type",required = true)
    private String tokenType;


    public RefreshTokenResponse(String refreshToken, String accessToken) {
        this.refreshToken = refreshToken;
        this.accessToken = accessToken;
        this.tokenType = "Bearer";
    }

    public static RefreshTokenResponse create(String refreshToken, String accessToken){
        return new RefreshTokenResponse(refreshToken,accessToken);
    }
}

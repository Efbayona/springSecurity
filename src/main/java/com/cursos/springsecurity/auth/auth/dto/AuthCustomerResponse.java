package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class AuthCustomerResponse {

    @JsonProperty(value = "token")
    private TokenResponse tokenResponse;

    @JsonProperty(value = "user")
    private UserCustomerResponse userCustomerResponse;

    @JsonProperty(value = "modules")
    private List<AuthModule> modules;

    @JsonProperty(value = "permissions")
    private List<Permissions> permissions;

    public AuthCustomerResponse(TokenResponse tokenResponse, UserCustomerResponse userCustomerResponse, List<AuthModule> modules, List<Permissions> permissions) {
        this.tokenResponse = tokenResponse;
        this.userCustomerResponse = userCustomerResponse;
        this.modules = modules;
        this.permissions = permissions;
    }

    public static AuthCustomerResponse create(TokenResponse tokenResponse, UserCustomerResponse userCustomerResponse, List<AuthModule> modules, List<Permissions> permissions) {
        return new AuthCustomerResponse(tokenResponse,
                userCustomerResponse,
                modules,
                permissions
        );
    }

}

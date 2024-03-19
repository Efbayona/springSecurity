package com.cursos.springsecurity.auth.auth.dto;

import com.cursos.springsecurity.auth.user.entity.User;
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
    private List<Permission> permissions;

    public AuthCustomerResponse(TokenResponse tokenResponse, UserCustomerResponse userCustomerResponse, List<AuthModule> modules, List<Permission> permissions) {
        this.tokenResponse = tokenResponse;
        this.userCustomerResponse = userCustomerResponse;
        this.modules = modules;
        this.permissions = permissions;
    }

    public static AuthCustomerResponse create(TokenResponse tokenResponse, UserCustomerResponse userCustomerResponse, List<AuthModule> modules, List<Permission> permissions) {
        return new AuthCustomerResponse(tokenResponse,
                userCustomerResponse,
                modules,
                permissions
        );
    }

}

package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class LoginCustomerResponseDto {

    @JsonProperty(value = "user_id")
    private UUID userId;

//    @JsonProperty(value = "mfa_is_email")
//    private boolean mfaIsEmail;

    @JsonProperty(value = "email")
    private String email;

    public LoginCustomerResponseDto(UUID userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public static LoginCustomerResponseDto create(UUID userId,  String email) {
        return new LoginCustomerResponseDto(userId, email);
    }

}

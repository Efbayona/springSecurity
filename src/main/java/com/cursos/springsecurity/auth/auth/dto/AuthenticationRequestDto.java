package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class AuthenticationRequestDto {

    @Size(max = 30)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "user_name")
    private String userName;

    /**
     * Contraseña del cliente.
     */
    @Size(max = 30)
    @NotNull
    @NotEmpty
    @JsonProperty(value = "user_password")
    private String userPassword;
}

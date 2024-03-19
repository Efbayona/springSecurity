package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.UUID;

/**
 * Solicitud de autenticación MFA (Autenticación Multifactor) para el cliente.
 */
@Getter
public class MfaRequest {

    @JsonProperty(value = "user_id")
    private UUID userId;

    @JsonProperty(value = "is_new_dispositive")
    private Boolean isNewDispositive;

    @JsonProperty(value = "user_code")
    @NotNull
    @NotEmpty
    private String code;

    @JsonProperty(value = "ip_address")
    private String ipAddress;
}

package com.cursos.springsecurity.auth.auth.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción lanzada cuando ocurre un error con el token de actualización.
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class RefreshTokenException extends RuntimeException{

    public RefreshTokenException(String message) {
        super(message);
    }
}

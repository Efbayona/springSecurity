package com.cursos.springsecurity.common.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Excepción que se lanza cuando no tiene permiso para ciertas acciones.
 */
@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class AccessDeniedException extends RuntimeException{

    public AccessDeniedException(String message){
        super(message);
    }
}

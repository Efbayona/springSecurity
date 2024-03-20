package com.cursos.springsecurity.common.exception_handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.I_AM_A_TEAPOT)  // 418
public class NotContentException extends RuntimeException {
}

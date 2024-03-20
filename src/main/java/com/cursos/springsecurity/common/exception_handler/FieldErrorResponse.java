package com.cursos.springsecurity.common.exception_handler;

/**
 * Clase que representa una respuesta de error de campo.
 */
public class FieldErrorResponse {

    private String field;
    private String message;

    /**
     * Constructor de la clase FieldErrorResponse.
     *
     * @param field   Nombre del campo con error.
     * @param message Mensaje de error asociado al campo.
     */
    public FieldErrorResponse(String field, String message) {
        this.field = field;
        this.message = message;
    }
}

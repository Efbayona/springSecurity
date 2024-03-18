package com.cursos.springsecurity.auth.user.status;

/**
 * Enumeración que representa los posibles estados de un usuario.
 */
public enum UserStatus {
    /**
     * Estado activo.
     */
    ACTIVE("Activo"),

    /**
     * Estado inactivo.
     */
    INACTIVE("Inactivo"),

    /**
     * Estado bloqueado.
     */
    LOCKED("Bloqueado");

    private String value;

    UserStatus(String value) {
        this.value = value;
    }

    /**
     * Devuelve el valor del estado.
     *
     * @return Valor del estado
     */
    public String getValue() {
        return value;
    }

    /**
     * Devuelve true si el estado es activo.
     *
     * @return true si el estado es activo, false de lo contrario
     */
    public boolean hasActive() {
        return this == ACTIVE;
    }

    /**
     * Devuelve el estado correspondiente al valor especificado.
     *
     * @param value Valor del estado
     * @return Estado correspondiente al valor especificado
     * @throws IllegalArgumentException si el valor no es compatible con ningún estado
     */
    public static UserStatus fromValue(String value) {
        return switch (value) {
            case "Activo" -> ACTIVE;
            case "Inactivo" -> INACTIVE;
            case "Bloqueado" -> LOCKED;
            default -> throw new IllegalArgumentException("UserStatus [" + value + "] not supported.");
        };
    }
}

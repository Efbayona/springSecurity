package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.util.UUID;

/**
 * Respuesta del usuario cliente.
 */
public class UserCustomerResponse {

    /**
     * ID del usuario.
     */
    @JsonProperty(value = "user_id")
    private UUID userId;

    /**
     * Nombre del usuario.
     */
    @JsonProperty(value = "user_name")
    private String name;

    /**
     * Estado del usuario.
     */
    @JsonProperty(value = "user_status")
    private String status;

    /**
     * URL de la imagen de perfil del usuario.
     */
//    @JsonProperty(value = "user_profile_image")
//    private String profileImage;
//
//    /**
//     * Nombre del cliente.
//     */
//    @JsonProperty(value = "person_name")
//    private String personName;
//
//    /**
//     * Apellido del cliente.
//     */
//    @JsonProperty(value = "person_last_name")
//    private String personLastName;
//
//    /**
//     * Número de documento del cliente.
//     */
//    @JsonProperty(value = "person_document_number")
//    private String documentNumber;
//
//    /**
//     * Teléfono del cliente.
//     */
//    @JsonProperty(value = "person_phone")
//    private String phone;
//
//    /**
//     * Correo electrónico del cliente.
//     */
//    @JsonProperty(value = "person_email")
//    private String email;
//
//    /**
//     * Dirección de la persona.
//     */
//    @JsonProperty(value = "person_direction")
//    private String direction;
//
//    /**
//     * Fecha de nacimiento del cliente.
//     */
//    @JsonProperty(value = "person_birthdate")
//    private LocalDate birthdate;
//
//    /**
//     * Ciudad de la persona
//     */
//    @NotBlank
//    @Size(max = 100)
//    @JsonProperty(value = "person_city")
//    private String city;


    /**
     * Constructor de UserCustomerResponse.
     *
     * @param userId                  ID del usuario.
     * @param name                    Nombre del usuario.
     * @param status                  Estado del usuario.
     * @param profileImage            URL de la imagen de perfil del usuario.
     * @param personName           Nombre de la persona.
     * @param personLastName       Apellido del cliente.
     * @param documentNumber Número de documento del cliente.
     * @param phone          Teléfono del cliente.
     * @param email          Correo electrónico del cliente.
     * @param direction      Dirección.
     * @param birthdate      Fecha de nacimiento del cliente.
     */
    public UserCustomerResponse(UUID userId, String name, String status, String profileImage, String personName,
                                String personLastName, String documentNumber, String phone, String email, String direction,
                                LocalDate birthdate, String city) {
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public static UserCustomerResponse create(UUID userId, String nameUser, String status) {
        return new UserCustomerResponse(
                userId,
                nameUser,
                status
        );
    }

    public UserCustomerResponse(UUID userId, String name, String status) {
        this.userId = userId;
        this.name = name;
        this.status = status;
    }

    public static  UserCustomerResponse response(UUID userId, String name, String status){
        return new UserCustomerResponse(
                userId,
                name,
                status
        );
    }
}

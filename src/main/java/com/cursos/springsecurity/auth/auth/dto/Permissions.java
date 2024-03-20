package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permissions {

    @JsonProperty(value = "name")
    private String name;

    public Permissions(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

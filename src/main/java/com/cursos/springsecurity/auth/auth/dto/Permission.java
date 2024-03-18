package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Permission {

    @JsonProperty(value = "name")
    private String name;

    public Permission(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

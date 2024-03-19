package com.cursos.springsecurity.auth.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class AuthModule {
    @JsonProperty(value = "module_id")
    private UUID moduleId;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "description")
    private String description;

    @JsonProperty(value = "module_icon")
    private String icon;

    @JsonProperty(value = "module_route")
    private String route;

//    @JsonProperty(value = "module_order")
//    private Integer order;

    public AuthModule(UUID moduleId, String name, String description, String icon, String route) {
        this.moduleId = moduleId;
        this.name = name;
        this.description = description;
        this.icon = icon;
        this.route = route;
    }
}

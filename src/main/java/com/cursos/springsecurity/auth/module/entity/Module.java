package com.cursos.springsecurity.auth.module.entity;

import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.UUID;

@Getter
@Entity
@Table(name = "module", schema = "main")
public class Module extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "module_id")
    private UUID moduleId;

    @Column(name = "name", unique = true, length = 50, nullable = false)
    private String name;


    @Column(name = "description", unique = true, length = 200, nullable = false)
    private String description;


    @Column(name = "icon", length = 50, nullable = false)
    private String icon;


    @Column(name = "route", unique = true, length = 50, nullable = false)
    private String route;

}

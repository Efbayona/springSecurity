package com.cursos.springsecurity.auth.module.entity;

import com.cursos.springsecurity.auth.permission.entity.Permission;
import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "module", schema = "main")
public class Module extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "module_id")
    private UUID moduleId;

    @Column(name = "module_name", unique = true, length = 50, nullable = false)
    private String moduleName;

    @Column(name = "module_description", unique = true, length = 200, nullable = false)
    private String moduleDescription;

    @Column(name = "module_icon", length = 50, nullable = false)
    private String moduleIcon;

    @Column(name = "module_route", unique = true, length = 50, nullable = false)
    private String moduleRoute;

    @Column(name = "module_order", nullable = false)
    private Integer moduleOrder;

    @OneToMany(mappedBy = "module")
    private List<Permission> permissions;

    public Module() {
    }

    public Module(UUID moduleId, String moduleName, String moduleDescription, String moduleIcon, String moduleRoute, Integer moduleOrder) {
        this.moduleId = moduleId;
        this.moduleName = moduleName;
        this.moduleDescription = moduleDescription;
        this.moduleIcon = moduleIcon;
        this.moduleRoute = moduleRoute;
        this.moduleOrder = moduleOrder;
    }
}

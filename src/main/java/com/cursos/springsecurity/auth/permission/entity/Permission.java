package com.cursos.springsecurity.auth.permission.entity;

import com.cursos.springsecurity.auth.module.entity.Module;
import com.cursos.springsecurity.auth.role.entity.Role;
import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "permission", schema = "main")
public class Permission extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "permission_id" )
    private UUID permissionId;

    @Column(name = "permission_name", length = 100 , nullable = false)
    private String permissionName;

    @Column(name = "permission_title", length = 100 , nullable = false)
    private String permissionTitle;

    @ManyToMany(mappedBy = "permissions")
    @Fetch(FetchMode.SUBSELECT)
    private List<Role> roles;

    @Column(name = "module_id", nullable = false)
    private UUID moduleId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "module_id", nullable = false,insertable = false, updatable = false)
    private Module module;

    public Permission() {
    }
    public Permission(String permissionName, String permissionTitle, Module module) {
        this.permissionName = permissionName;
        this.permissionTitle = permissionTitle;
        this.module = module;
    }
}

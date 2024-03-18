package com.cursos.springsecurity.auth.permission.entity;

import com.cursos.springsecurity.auth.role.entity.Role;
import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;

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

    @Column(name = "name", length = 100 , nullable = false)
    private String name;

    @Column(name = "title", length = 100 , nullable = false)
    private String title;

    @ManyToMany(mappedBy = "permissions")
    private List<Role> roles;

}

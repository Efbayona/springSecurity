package com.cursos.springsecurity.auth.role.entity;

import com.cursos.springsecurity.auth.permission.entity.Permission;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.common.util.AuditEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "role", schema = "main")
public class Role extends AuditEntity {

    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private UUID roleId;

    @Column(name = "rolename", length = 100, nullable = false)
    private String roleName;

    @ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_permission", schema = "main",
            joinColumns = @JoinColumn(name = "role_id", referencedColumnName = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id", referencedColumnName = "permission_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"role_id", "permission_id"}, name = "uc_role_permission"))
    private List<Permission> permissions;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;
}

package com.cursos.springsecurity.auth.user.repository;

import com.cursos.springsecurity.auth.auth.dto.Permission;

import java.util.List;

public interface UserRepositoryCustom {

    List<Permission> findPermissionsByUserName(String userName);
}

package com.cursos.springsecurity.auth.user.repository;

import com.cursos.springsecurity.auth.auth.dto.AuthModule;
import com.cursos.springsecurity.auth.auth.dto.Permissions;

import java.util.List;
import java.util.UUID;

public interface UserRepositoryCustom {

    List<Permissions> findPermissionsByUserName(String userName);

    List<AuthModule> findModulesByUser(UUID userId);
}

package com.cursos.springsecurity.auth.user.repository.impl;

import com.cursos.springsecurity.auth.auth.dto.Permission;
import com.cursos.springsecurity.auth.user.repository.UserRepositoryCustom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
    @Override
    public List<Permission> findPermissionsByUserName(String userName) {
        return null;
    }
}

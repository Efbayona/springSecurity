package com.cursos.springsecurity.auth.user.repository;

import com.cursos.springsecurity.auth.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository <User, UUID> , UserRepositoryCustom {

    User getUserByUserName(String name);

}

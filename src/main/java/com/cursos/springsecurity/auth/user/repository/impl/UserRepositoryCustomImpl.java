package com.cursos.springsecurity.auth.user.repository.impl;

import com.cursos.springsecurity.auth.auth.dto.AuthModule;
import com.cursos.springsecurity.auth.auth.dto.Permission;
import com.cursos.springsecurity.auth.role.entity.Role;
import com.cursos.springsecurity.auth.user.entity.User;
import com.cursos.springsecurity.auth.user.repository.UserRepositoryCustom;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
@Repository
public class UserRepositoryCustomImpl implements UserRepositoryCustom {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Permission> findPermissionsByUserName(String userName) {
        return null;
    }

    @Override
    public List<AuthModule> findModulesByUser(UUID userId) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        List<AuthModule> modules = new ArrayList<>();

        try {
            /* --- Create query ---*/
            CriteriaQuery<AuthModule> cq = cb.createQuery(AuthModule.class);
            /*--- Define FROM clause ---*/
            Root<User> root = cq.from(User.class);
            Join<User, Role> userRoleJoin = root.join("roles", JoinType.INNER);
            Join<Role, Permission> permissionJoin = userRoleJoin.join("permissions", JoinType.INNER);
            Join<Permission, Module> moduleJoin = permissionJoin.join("module", JoinType.INNER);

            /* --- Define DTO projection ---*/
            cq.select(cb.construct(
                    AuthModule.class,
                    root.get("moduleId"),
                    root.get("moduleName"),
                    root.get("moduleDescription"),
                    root.get("moduleIcon"),
                    root.get("moduleRoute"),
                    root.get("moduleOrder")
            ));

            /* --- Define WHERE clause ---*/
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userId"), userId));

            cq.where(predicates.toArray(new Predicate[0])).orderBy(cb.asc(moduleJoin.get("moduleOrder")));

            /* Agrupar por atributos de la entidad Module*/
            cq.groupBy(
                    root.get("moduleId"),
                    root.get("moduleName"),
                    root.get("moduleDescription"),
                    root.get("moduleIcon"),
                    root.get("moduleRoute"),
                    root.get("moduleOrder")
            );

            /* --- Execute query ---*/
            TypedQuery<AuthModule> q = em.createQuery(cq);
            modules = q.getResultList();

        } catch (Exception ex) {
            log.error("Error al obtener los módulos por usuario {}", ex.getMessage());
        }
        em.close();
        return modules;
    }
}

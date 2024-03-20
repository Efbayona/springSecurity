package com.cursos.springsecurity.auth.user.repository.impl;

import com.cursos.springsecurity.auth.auth.dto.AuthModule;
import com.cursos.springsecurity.auth.auth.dto.Permissions;
import com.cursos.springsecurity.auth.module.entity.Module;
import com.cursos.springsecurity.auth.permission.entity.Permission;
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
    public List<Permissions> findPermissionsByUserName(String userName) {


        CriteriaBuilder cb = em.getCriteriaBuilder();

        List<Permissions> permissions = new ArrayList<>();

        try {
            /*-- Criteria Query --*/
            CriteriaQuery<Permissions> cq = cb.createQuery(Permissions.class);

            /*-- Define FROM clause --*/
            Root<User> root = cq.from(User.class);
            Join<User , Role> userRoleJoin = root.join("roles", JoinType.INNER);
            Join<Role , Permission> permissionJoin = userRoleJoin.join( "permissions" , JoinType.INNER);


            cq.select(cb.construct(
                    Permissions.class,
                    permissionJoin.get("permissionName")
            ));

            /*-- Define WHERE clause --*/
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userName") , userName));

            cq.where(predicates.toArray(new Predicate[0]));

            cq.groupBy(permissionJoin.get("permissionName"));

            /*-- Execute Query --*/
            TypedQuery<Permissions> q = em.createQuery(cq);
            permissions = q.getResultList();

        } catch (Exception e){
            log.error("Criteria Permissions Login [{}]" , e.getMessage());
        }
        em.close();
        return permissions;

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
            Join<Permissions, Module> moduleJoin = permissionJoin.join("module", JoinType.INNER);

            /* --- Define DTO projection ---*/
            cq.select(cb.construct(
                    AuthModule.class,
                    moduleJoin.get("moduleId"),
                    moduleJoin.get("moduleName"),
                    moduleJoin.get("moduleDescription"),
                    moduleJoin.get("moduleIcon"),
                    moduleJoin.get("moduleRoute"),
                    moduleJoin.get("moduleOrder")
            ));

            /* --- Define WHERE clause ---*/
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(cb.equal(root.get("userId"), userId));

            cq.where(predicates.toArray(new Predicate[0])).orderBy(cb.asc(moduleJoin.get("moduleOrder")));

            /* Agrupar por atributos de la entidad Module*/
            cq.groupBy(
                    moduleJoin.get("moduleId"),
                    moduleJoin.get("moduleName"),
                    moduleJoin.get("moduleDescription"),
                    moduleJoin.get("moduleIcon"),
                    moduleJoin.get("moduleRoute"),
                    moduleJoin.get("moduleOrder")
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

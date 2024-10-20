package ru.vagapov.spring.service.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.entity.RoleEntity;
import ru.vagapov.spring.entity.UserEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Сервис слоя для работы с ролями пользователей
 */
@Service
public class UserRolesService {

    @PersistenceContext
    private EntityManager entityManager;

    public Set <RoleEntity> roleOfDtoToRoleEntity(List<String> dtoRoles){
        Set<RoleEntity> entitiesRoles = new HashSet<>();
        for (String role : dtoRoles) {
            int id = 0;
            if (role.equals("ROLE_ADMIN")) {
                id = 2;
                RoleEntity roleEntity = entityManager.find(RoleEntity.class, id);
                entitiesRoles.add(roleEntity);
            } else if (role.equals("ROLE_USER")) {
                id =1;
                RoleEntity roleEntity = entityManager.find(RoleEntity.class, id);
                entitiesRoles.add(roleEntity);
            }
        }
        return entitiesRoles;
    }

    public List <String> roleOfEntityToRoleDto(Set<RoleEntity> entitiesRoles){
        List<String> dtoRoles = new ArrayList<>();
        for (RoleEntity role : entitiesRoles) {
            String roleName = role.getName();
            dtoRoles.add(roleName);
        }
        return dtoRoles;
    }

}

package ru.vagapov.spring.service.impl;

import org.springframework.stereotype.Service;
import ru.vagapov.spring.dto.Role;
import ru.vagapov.spring.entity.RoleEntity;
import ru.vagapov.spring.repository.RoleJPARepository;
import java.util.*;

/**
 * Сервис слоя для работы с ролями пользователей
 */
@Service
public class UserRolesService {
    private final RoleJPARepository roleJPARepository;

    public UserRolesService(RoleJPARepository roleJPARepository) {
        this.roleJPARepository = roleJPARepository;
    }

    public Set <RoleEntity> roleOfDtoToRoleEntity(List<String> dtoRoles){
        Set<RoleEntity> entitiesRoles = new HashSet<>();
        for (String role : dtoRoles) {
            if (role.equals("ROLE_ADMIN")) {
                entitiesRoles = roleJPARepository.findRolesByName(role);
            } else if (role.equals("ROLE_USER")) {
                entitiesRoles = roleJPARepository.findRolesByName(role);
            }
        }
        return entitiesRoles;
    }

    public List<String> roleOfEntityToRoleDto(Set<RoleEntity> entitiesRoles){
        List<String> dtoRoles = new ArrayList<>();
        for (RoleEntity role : entitiesRoles) {
            if (role.getName().equals("ROLE_ADMIN")) {
                dtoRoles.add(role.getName());
            } else if (role.getName().equals("ROLE_USER")) {
                dtoRoles.add(role.getName());
            }
        }
        return dtoRoles;
    }
    public Set<Role> roleOfEntityToRoleDtoForLogin(Set<RoleEntity> entitiesRoles){
        Set<Role> dtoRoles = new HashSet<>();
        for (RoleEntity role : entitiesRoles) {
            if (role.getName().equals("ROLE_ADMIN")) {
                Role roleLogin = new Role();
                roleLogin.setName(role.getName());
                roleLogin.setId(role.getId());
                dtoRoles.add(roleLogin);
            } else if (role.getName().equals("ROLE_USER")) {
                Role roleLogin = new Role();
                roleLogin.setName(role.getName());
                roleLogin.setId(role.getId());
                dtoRoles.add(roleLogin);
            }
        }
        return dtoRoles;
    }

    public Set<Role> findAllRoles(){
       List<RoleEntity> roles = roleJPARepository.findAll();
       Set<Role> dtoRoles = new HashSet<>();
       for (RoleEntity role : roles) {
           Role roleOfUser = new Role();
           roleOfUser.setId(role.getId());
           roleOfUser.setName(role.getName());
           dtoRoles.add(roleOfUser);
       }
       return dtoRoles;
    }
}

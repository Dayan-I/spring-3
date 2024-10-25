package ru.vagapov.spring.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.mappingUtils.MappingUtils;
import ru.vagapov.spring.service.UserService;
import java.util.List;
import java.util.Optional;
import ru.vagapov.spring.repository.UserDaoJPARepository;


@Service
public class UserServiceImpl implements UserService {

    private final MappingUtils mappingUtils;
    private final UserDaoJPARepository userDaoJPARepository;
    private final UserRolesService userRolesService;

    public UserServiceImpl(MappingUtils mappingUtils, UserDaoJPARepository userDaoJPAIRepository, UserRolesService userRolesService) {
        this.mappingUtils = mappingUtils;
        this.userDaoJPARepository = userDaoJPAIRepository;
        this.userRolesService = userRolesService;
    }

    @Override
    public void createUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userEntity.setRoles(userRolesService.roleOfDtoToRoleEntity(user.getRoles()));
        userDaoJPARepository.save(userEntity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        List<String > userRole = user.getRoles();
        userEntity.setRoles(userRolesService.roleOfDtoToRoleEntity(userRole));
        userDaoJPARepository.save(userEntity);
    }

    @Override
    public void deleteUser(Long id) {
        userDaoJPARepository.deleteById(id);
    }

    @Override
    public User findById(Long id) {
       User user = mappingUtils.userEntityToUserDto(userDaoJPARepository.findById(id));
       user.setRoles(userRolesService.roleOfEntityToRoleDto(userDaoJPARepository.findById(id).get().getRoles()));
       return user;
    }

    @Override
    public User findUserByUserName(String userName) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(userName);
        User user= mappingUtils.userEntityToUserDto(Optional.ofNullable(userEntity));
        user.setRoles(userRolesService.roleOfEntityToRoleDto(userDaoJPARepository.findUserRoles(user.getId())));
        return user;
    }

    @Override
    public List<User> findAll() {
       List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAll());
       for (User user : users) {
           user.setRoles(userRolesService.roleOfEntityToRoleDto(userDaoJPARepository.findById(user.getId()).get().getRoles()));
       }
       return users;
    }

    @Override
    public List<User> findAllUsersByLastName(String lastName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllByLastName(lastName));
        for (User user : users) {
            user.setRoles(userRolesService.roleOfEntityToRoleDto(userDaoJPARepository.findById(user.getId()).get().getRoles()));
        }
        return users;
    }

    @Override
    public List<User> findAllUsersByPartOfNameOrLastName(String partOfName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersByPartOfNameOrLastName(partOfName));
        for (User user : users) {
            user.setRoles(userRolesService.roleOfEntityToRoleDto(userDaoJPARepository.findById(user.getId()).get().getRoles()));
        }
        return users;
    }

    @Override
    public UserForLogin findUserByUserNameForLogin(String name) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(name);
        UserForLogin user = mappingUtils.userDtoToUserEntityLogin(userEntity);
        user.setAuthorities(userRolesService.roleOfEntityToRoleDtoForLogin(userEntity.getRoles()));
        return user;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserForLogin user = findUserByUserNameForLogin(username);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return user;
    }
}

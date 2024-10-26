package ru.vagapov.spring.service.impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;
import ru.vagapov.spring.entity.RoleEntity;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.mappingUtils.MappingUtils;
import ru.vagapov.spring.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
        userDaoJPARepository.save(userEntity);
    }

    @Override
    public void updateUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
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
    @Transactional(readOnly = true)
    public User findUserByUserName(String userName) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(userName);
        return mappingUtils.userEntityToUserDto(Optional.ofNullable(userEntity));
    }

    @Override
    public List<User> findAll() {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersWithRoles());
    }

    @Override
    public List<User> findAllUsersByLastName(String lastName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllByLastName(lastName));
        return users;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAllUsersByPartOfNameOrLastName(String partOfName) {
        List<User> users = mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPARepository.findAllUsersByPartOfNameOrLastName(partOfName));
        return users;
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

    private UserForLogin findUserByUserNameForLogin(String name) {
        UserEntity userEntity = userDaoJPARepository.findByUserName(name);
        UserForLogin user = mappingUtils.userDtoToUserEntityLogin(userEntity);
        user.setAuthorities(userRolesService.roleOfEntityToRoleDtoForLogin(userEntity.getRoles()));
        return user;
    }
}

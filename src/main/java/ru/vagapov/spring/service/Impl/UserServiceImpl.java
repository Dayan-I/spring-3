package ru.vagapov.spring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.mappingUtils.MappingUtils;
import ru.vagapov.spring.service.UserService;
import java.util.List;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final MappingUtils mappingUtils;

    @Autowired
    public UserServiceImpl(UserDao userDao, MappingUtils mappingUtils) {
        this.userDao = userDao;
        this.mappingUtils = mappingUtils;
    }

    @Override
    public void createUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userDao.createUser(userEntity);
    }

    @Override
    public void updateUser(User user, Long id) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userDao.updateUser(userEntity, id);
    }

    @Override
    public void deleteUser(Long id) {
        userDao.deleteUser(id);
    }

    @Override
    public User findById(Long id) {
        return mappingUtils.userEntityToUserDto(userDao.findById(id));
    }

    @Override
    public User findUserByUserName(String userName) {
        return mappingUtils.userEntityToUserDto(userDao.findUserByUserName(userName));
    }

    @Override
    public List<User> findAll() {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDao.findAll());
    }

    @Override
    public List<User> findAllUsersByLastName(String lastName) {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDao.findAllUsersByLastName(lastName));
    }

    @Override
    public List<User> findAllUsersByPartOfNameOrLastName(String partOfName) {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDao.findAllUsersByPartOfNameOrLastName(partOfName));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userDao.findUserByUserName(username);
        if (userEntity == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }

        return new org.springframework.security.core.userdetails.User(userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getAuthorities());
    }
}

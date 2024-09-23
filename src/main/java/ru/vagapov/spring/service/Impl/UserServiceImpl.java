package ru.vagapov.spring.service.Impl;

import org.springframework.stereotype.Service;
import ru.vagapov.spring.MappingUtils.MappingUtils;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;
    private final MappingUtils mappingUtils = new MappingUtils();

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
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
        List<UserEntity> user_list= userDao.findAll();
        List<User> users = new ArrayList<User>();
        for (UserEntity user : user_list) {
            users.add(mappingUtils.userEntityToUserDto(user));
        }
        return users;
    }


    @Override
    public List<User> findAllUsersByLastName(String lastName) {
        List<UserEntity> user_list= userDao.findAllUsersByLastName(lastName);
        List<User> users = new ArrayList<User>();
        for (UserEntity user : user_list) {
            users.add(mappingUtils.userEntityToUserDto(user));
        }
        return users;
    }
}

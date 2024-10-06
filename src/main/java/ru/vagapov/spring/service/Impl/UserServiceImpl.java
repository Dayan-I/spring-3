package ru.vagapov.spring.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.vagapov.spring.MappingUtils.MappingUtils;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.service.UserService;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    @Autowired
    private MappingUtils mappingUtils;

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
}

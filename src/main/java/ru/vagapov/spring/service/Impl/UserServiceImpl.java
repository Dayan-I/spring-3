package ru.vagapov.spring.service.Impl;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dao.Impl.UserDaoJPAImpl;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;
import ru.vagapov.spring.mappingUtils.MappingUtils;
import ru.vagapov.spring.service.UserService;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserDao userDao;
    private final MappingUtils mappingUtils;
    private final UserDaoJPAImpl userDaoJPAImpl;


    public UserServiceImpl(UserDao userDao, MappingUtils mappingUtils, UserDaoJPAImpl userDaoJPAImpl) {
        this.userDao = userDao;
        this.mappingUtils = mappingUtils;
        this.userDaoJPAImpl = userDaoJPAImpl;
    }

    @Override
    public void createUser(User user) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userDaoJPAImpl.save(userEntity);
    }

    @Override
    public void updateUser(User user, Long id) {
        UserEntity userEntity = mappingUtils.userDtoToUserEntity(user);
        userDaoJPAImpl.updateUserEntityById(userEntity,id);
    }

    @Override
    public void deleteUser(Long id) {
        userDaoJPAImpl.deleteById(id);
    }

    @Override
    public User findById(Long id) {
        return mappingUtils.userEntityToUserDto(userDaoJPAImpl.findById(id));
    }

    @Override
    public User findUserByUserName(String userName) {
        return mappingUtils.userEntityToUserDto(Optional.ofNullable(userDaoJPAImpl.findByUserName(userName)));
    }

    @Override
    public List<User> findAll() {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPAImpl.findAll());
    }

    @Override
    public List<User> findAllUsersByLastName(String lastName) {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPAImpl.findAllByLastName(lastName));
    }

    @Override
    public List<User> findAllUsersByPartOfNameOrLastName(String partOfName) {
        return mappingUtils.listOfUserEntityToListOfUserDto(userDaoJPAImpl.findAllUsersByPartOfName(partOfName));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userDto = findUserByUserName(username);
        UserEntity user = mappingUtils.userDtoToUserEntityLogin(userDto);
        if (user == null) {
            throw new UsernameNotFoundException(String.format("User %s not found", username));
        }
        return user;
    }
}

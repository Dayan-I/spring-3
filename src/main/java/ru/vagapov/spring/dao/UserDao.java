package ru.vagapov.spring.dao;

import ru.vagapov.spring.entity.UserEntity;
import java.util.List;

/**
 * Интерфейс для взаимодействия с БД, работает с UserEntity
 */
public interface UserDao {
    void createUser(UserEntity user);

    void updateUser(UserEntity user, Long id);

    void deleteUser(Long id);

    UserEntity findById(Long id);

    UserEntity findUserByUserName(String userName);

    List<UserEntity> findAll();

    List<UserEntity> findAllUsersByLastName(String lastName);
}

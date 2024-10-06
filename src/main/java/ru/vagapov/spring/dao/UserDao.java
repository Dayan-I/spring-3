package ru.vagapov.spring.dao;

import ru.vagapov.spring.entity.UserEntity;
import java.util.List;

/**
 * DAO Интерфейс для взаимодействия с БД
 */
public interface UserDao {
    /**
     * Создание нового пользователя
     */
    void createUser(UserEntity user);

    /**
     * Обновления пользователя по айди
     */
    void updateUser(UserEntity user, Long id);

    /**
     * Удаление пользователя
     */
    void deleteUser(Long id);

    /**
     * Поиск пользователя по айди
     */
    UserEntity findById(Long id);

    /**
     * Поиск пользователя по имени
     */
    UserEntity findUserByUserName(String userName);

    /**
     * Возвращает всех пользователей из БД
     */
    List<UserEntity> findAll();

    /**
     * Поиск пользоваталей по фамилии
     */
    List<UserEntity> findAllUsersByLastName(String lastName);

    /**
     * Поиск пользователей по части имени или фамилии
     */
    List<UserEntity> findAllUsersByPartOfNameOrLastName(String partOfName);
}

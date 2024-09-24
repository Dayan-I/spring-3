package ru.vagapov.spring.dao;

import ru.vagapov.spring.entity.UserEntity;
import java.util.List;

/**
 * DAO Интерфейс для взаимодействия с БД
 */
public interface UserDao {
    /**
     * Создание нового юзера
     */
    void createUser(UserEntity user);

    /**
     * Обновления юзера по айди
     */
    void updateUser(UserEntity user, Long id);

    /**
     * Удаление юзера
     */
    void deleteUser(Long id);

    /**
     * Поиск юзера по айди
     */
    UserEntity findById(Long id);

    /**
     * Поиск юзера по имени
     */
    UserEntity findUserByUserName(String userName);

    /**
     * Возвращает всех юзеров из БД
     */
    List<UserEntity> findAll();

    /**
     * Поиск юзеров по фамилии
     */
    List<UserEntity> findAllUsersByLastName(String lastName);
}

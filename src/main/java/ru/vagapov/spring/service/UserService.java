package ru.vagapov.spring.service;

import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.entity.UserEntity;

import java.util.List;

/**
 * Сервисный слой, реализует методы DAO слоя
 */

public interface UserService {
    /**
     * Создание нового юзера
     *
     * @param user Новый пользователь
     */
    void createUser(User user);

    /**
     * Обволение айди юзера
     *
     * @param user пользоветель у которого меняем айди
     * @param id   новый айди
     */
    void updateUser(User user, Long id);

    /**
     * Удаление пользователя из БД
     *
     * @param id айди пользователя которого надо удалить
     */
    void deleteUser(Long id);

    /**
     * Поиск пользователя по айди
     *
     * @param id - айди пользователя которого надо найти
     * @return User нужный пользователь
     */
    User findById(Long id);

    /**
     * Поиск пользователя по имени
     *
     * @param userName - имя пользователя которого надо найти
     * @return User нужный пользователь
     */
    User findUserByUserName(String userName);

    /**
     * Поиск всех пользователей
     *
     * @return List<User> список всех пользователей
     */
    List<User> findAll();

    /**
     * Поиск пользователей по фамилии
     *
     * @return List<User>  список всех пользователей с искомой фамилией
     */
    List<User> findAllUsersByLastName(String lastName);

    List<User> findAllUsersByPartOfNameOrLastName(String partOfName);
}

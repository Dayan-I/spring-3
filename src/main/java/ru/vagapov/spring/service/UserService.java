package ru.vagapov.spring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vagapov.spring.dto.User;
import ru.vagapov.spring.dto.UserForLogin;

import java.util.List;

/**
 * Сервисный слой, реализует методы DAO слоя
 */

public interface UserService extends UserDetailsService {
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
     *
     */
    void updateUser(User user);

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
     * @param lastName фамилия для поиска
     */
    List<User> findAllUsersByLastName(String lastName);

    /**
     * Поиск пользователей по фамилии
     *
     * @return List<User>  список всех пользователей с частью искомой строки
     * @param partOfName что должны содержать имя или фамилия
     */
    List<User> findAllUsersByPartOfNameOrLastName(String partOfName);

    UserForLogin findUserByUserNameForLogin(String name);
}

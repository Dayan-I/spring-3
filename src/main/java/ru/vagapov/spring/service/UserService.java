package ru.vagapov.spring.service;

import ru.vagapov.spring.dto.User;

import java.util.List;

/**
 * Сервисный слой, реализует методы ДАО слоя
 * Работает с UserDTO
 */
public interface UserService {

    void createUser(User user);

    void updateUser(User user, Long id);

    void deleteUser(Long id);

    User findById(Long id);

    User findUserByUserName(String userName);

    List<User> findAll();

    List<User> findAllUsersByLastName(String lastName);
}

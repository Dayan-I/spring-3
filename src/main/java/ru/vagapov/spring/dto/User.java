package ru.vagapov.spring.dto;

import java.util.*;

/**
 * ДТО класс для сущности юзера, именно его отдаем по запросам
 * пользователей и в userService
 */
public class User  {
    /**
     * Уникальный айди
     */
    private Long id;
    /**
     * Имя
     */
    private String userName;
    /**
     * Фамилия
     */
    private String lastName;
    /**
     * Электронная почта
     */
    private String email;
    /**
     * Пароль от сервиса
     */
    private String password;
    /**
     * Возраст
     */
    private Integer age;
    /**
     * Список ролей пользователя
     */
    private List<String> roles;

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public User() {
    }

    public User(Long id, String userName, String lastName, String email, String password, Integer age, List<String> roles) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
        this.roles = roles;
    }

    public String getUserName() {
        return userName;
    }

    public List<String> getRoles() {
        return roles;
    }

    public Long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(userName, user.userName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(password, user.password) && Objects.equals(age, user.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, lastName, email, password, age);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", userName='" + userName + '\'' +
               ", lastName='" + lastName + '\'' +
               ", email='" + email + '\'' +
               ", password='" + password + '\'' +
               ", age=" + age +
               '}';
    }


    public String getPassword() {
        return password;
    }

}


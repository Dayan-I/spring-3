package ru.vagapov.spring.dto;

import java.util.Objects;

/**
 * ДТО класс для сущности юзера, именно его отдаем по запросам
 * пользователей и в userService
 */
public class User {
    /**
     * уникальный айди
     */
    private Long id;
    /**
     * имя
     */
    private String userName;
    /**
     * фамилия
     */
    private String lastName;
    /**
     * электронная почта
     */
    private String email;
    /**
     * пароль от сервиса
     */
    private String password;
    /**
     * возраст
     */
    private Integer age;

    public User() {}

    public User(Long id, String userName, String lastName, String email, String password, Integer age) {
        this.id = id;
        this.userName = userName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
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
}

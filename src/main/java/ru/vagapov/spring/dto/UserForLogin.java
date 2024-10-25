package ru.vagapov.spring.dto;

import org.springframework.security.core.userdetails.UserDetails;
import java.util.Set;
/**
 * ДТО класс для сущности юзера для авторизации
 */
public class UserForLogin implements UserDetails {
    private String username;
    private String password;
    private Set<Role> authorities;

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthorities(Set<Role> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Set<Role> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }
}
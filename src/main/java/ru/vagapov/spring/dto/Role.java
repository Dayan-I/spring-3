package ru.vagapov.spring.dto;

import org.springframework.security.core.GrantedAuthority;

/**
 * ДТО класс для сущности роли
 *
 */
public class Role implements GrantedAuthority {

    private Long id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}

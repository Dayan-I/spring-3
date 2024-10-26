package ru.vagapov.spring.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "roles")
public final class RoleEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<UserEntity> users;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return name;
    }
}

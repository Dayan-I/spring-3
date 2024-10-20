package ru.vagapov.spring.entity;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.Set;

@Entity
@Table(name = "roles")
public class RoleEntity implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Override
    public String getAuthority() {
        return getName();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @ManyToMany
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "id"))
    private Set<UserEntity> users;

    public void setUsers(Set<UserEntity> users) {
        this.users = users;
    }

    public Set<UserEntity> getUsers() {
        return users;
    }

    @Override
    public String toString() {
        return name;
    }
}

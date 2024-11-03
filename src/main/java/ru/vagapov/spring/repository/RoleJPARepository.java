package ru.vagapov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vagapov.spring.entity.RoleEntity;
import java.util.List;
import java.util.Set;

/**
 * JPA Интерфейс для взаимодействия c ролями пользователей
 */
@Repository
public interface RoleJPARepository extends JpaRepository<RoleEntity, Long> {

    Set<RoleEntity> findRolesByName(String name);

    List<RoleEntity> findAll();
}

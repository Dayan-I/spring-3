package ru.vagapov.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vagapov.spring.entity.UserEntity;
import java.util.List;


/**
 * JPA Интерфейс для взаимодействия с БД
 */

@Repository
public interface UserDaoJPARepository extends JpaRepository<UserEntity, Long> {

    UserEntity findByUserName(String username);

    List<UserEntity> findAllByLastName(String lastName);


    @Query("SELECT u FROM UserEntity u WHERE upper (u.lastName) LIKE concat('%',upper(:partOfName),'%') OR upper(u.userName) LIKE concat('%',upper(:partOfName) ,'%')")
    List<UserEntity> findAllUsersByPartOfNameOrLastName(String partOfName);

    @Query("SELECT u FROM UserEntity u join fetch u.roles")
    List<UserEntity> findAllUsersWithRoles();

    @Query("SELECT u FROM UserEntity u join u.books b where b.id = :id")
    List<UserEntity> findAllUsersWithBook(Long id);

}



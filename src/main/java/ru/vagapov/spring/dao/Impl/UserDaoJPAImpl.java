package ru.vagapov.spring.dao.Impl;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.vagapov.spring.entity.UserEntity;
import java.util.List;

/**
 * JPA Интерфейс для взаимодействия с БД
 */
@Repository
public interface UserDaoJPAImpl extends JpaRepository<UserEntity, Long> {

    public UserEntity findByUserName(String username);

    public List<UserEntity> findAllByLastName(String lastName);

    @Query("SELECT u FROM UserEntity u WHERE upper (u.lastName) LIKE concat('%',upper(:partOfName),'%') OR upper(u.userName) LIKE concat('%',upper(:partOfName) ,'%')")
    List<UserEntity> findAllUsersByPartOfNameOrLastName(String partOfName);

    default List<UserEntity> findAllUsersByPartOfName(String partOfName){
        return findAllUsersByPartOfNameOrLastName(partOfName);
    };

    default void updateUserEntityById(UserEntity userEntity, Long id) {
        UserEntity userToUpdate = findById(id).orElseThrow(EntityNotFoundException::new);
        userToUpdate.setLastName(userEntity.getLastName());
        userToUpdate.setUserName(userEntity.getUserName());
        userToUpdate.setRoles(userEntity.getAuthorities());
        userToUpdate.setEmail(userEntity.getEmail());
        userToUpdate.setPassword(userEntity.getPassword());
        userToUpdate.setAge(userEntity.getAge());
        save(userToUpdate);
    }
}



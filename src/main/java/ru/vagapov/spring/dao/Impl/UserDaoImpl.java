package ru.vagapov.spring.dao.Impl;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.entity.UserEntity;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createUser(UserEntity user) {
        entityManager.persist(user);
    }

    @Transactional
    @Override
    public void updateUser(UserEntity user, Long id) {
        UserEntity userEntity = entityManager.find(UserEntity.class, id);
        userEntity.setUserName(user.getUserName());
        userEntity.setPassword(user.getPassword());
        userEntity.setEmail(user.getEmail());
        userEntity.setLastName(user.getLastName());
        userEntity.setAge(user.getAge());
        entityManager.merge(userEntity);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        entityManager.remove(entityManager.find(UserEntity.class, id));
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Transactional(readOnly = true)
    @Override
    public UserEntity findUserByUserName(String userName) {
        String jpql = "SELECT u FROM UserEntity u WHERE u.userName = :userName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("userName", userName);
        return (UserEntity) query.getSingleResult();
//         можно сделать через критерию:
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
//        Root<UserEntity> root = cq.from(UserEntity.class);
//        cq.select(root);
//        cq.where(cb.equal(root.get("userName"), userName));
//        TypedQuery<UserEntity> query = entityManager.createQuery(cq);
//        return query.getSingleResult();

    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("FROM UserEntity").getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAllUsersByLastName(String lastName) {
        String jpql = "SELECT u FROM UserEntity u WHERE u.lastName = :lastName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserEntity> findAllUsersByPartOfNameOrLastName(String partOfName) {
        String jpql = """
                SELECT u FROM UserEntity u WHERE upper (u.lastName) LIKE concat('%',upper(:partOfName),'%')\s
                OR upper(u.userName) LIKE concat('%',upper(:partOfName) ,'%')""";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("partOfName", partOfName);
        return query.getResultList();
    }

}


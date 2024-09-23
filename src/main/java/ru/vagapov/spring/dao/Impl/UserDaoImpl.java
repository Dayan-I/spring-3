package ru.vagapov.spring.dao.Impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.vagapov.spring.dao.UserDao;
import ru.vagapov.spring.entity.UserEntity;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void createUser(UserEntity user) {
        entityManager.merge(user);
    }

    @Transactional
    @Override
    public void updateUser(UserEntity user, Long id) {
        entityManager.setProperty("id", id);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        entityManager.detach(entityManager.getReference(UserEntity.class, id));
    }

    @Transactional
    @Override
    public UserEntity findById(Long id) {
        return entityManager.find(UserEntity.class, id);
    }

    @Transactional
    @Override
    public UserEntity findUserByUserName(String userName) {
        String jpql = "select u from UserEntity u where u.userName = :userName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("userName", userName);
        return (UserEntity) query.getSingleResult();
        // можно сделать через критерию:
       /* CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
        Root<UserEntity> root = cq.from(UserEntity.class);
        cq.select(root);
        cq.where(cb.equal(root.get("userName"), userName));
        TypedQuery<UserEntity> query = entityManager.createQuery(cq);
        return query.getSingleResult();*/
    }

    @Transactional
    @Override
    public List<UserEntity> findAll() {
        return entityManager.createQuery("from UserEntity").getResultList();
    }

    @Transactional
    @Override
    public List<UserEntity> findAllUsersByLastName(String lastName) {
        String jpql = "select u from UserEntity u where u.lastName = :lastName";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("lastName", lastName);
        return query.getResultList();
    }
}


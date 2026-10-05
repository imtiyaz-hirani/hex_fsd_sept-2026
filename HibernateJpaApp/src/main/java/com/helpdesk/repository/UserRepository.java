package com.helpdesk.repository;

import com.helpdesk.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    public void insert(User user) { //100X
        entityManager.persist(user); // 100X
    }

    public List<User> getUserByUsername(String username) {
        String jpql ="select u from User u where u.username=?1";

       return entityManager.createQuery(jpql , User.class)
                .setParameter(1,username)
                .getResultList();

    }
}

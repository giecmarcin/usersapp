package com.mgiec.repositories;

import com.mgiec.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(User user) {
        entityManager.persist(user);
    }

    @Override
    public com.mgiec.entities.User edit(com.mgiec.entities.User user) {
        return entityManager.merge(user);
    }

    @Override
    public void remove(Long id) {
        Optional<User> oneUser = findOne(id);
        if (oneUser.isPresent())
            entityManager.remove(oneUser.get());
    }

    @Override
    public Optional<User> findOne(Long id) {
        User u = entityManager.find(User.class, id);
        return Optional.ofNullable(u);
    }

    @Override
    public List<User> findAll() {
        TypedQuery<User> query = entityManager.createQuery("select u from User u", User.class);
        return query.getResultList();
    }

    @Override
    public Optional<User> findByName(String name) {
        TypedQuery query = entityManager.createQuery("select u from User u where u.name=:name", User.class);
        query.setParameter("name", name);
        List<User> users = query.getResultList();
        return users.stream().findFirst();
    }
}

package com.mgiec.repositories;

import com.mgiec.entities.Group;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class GroupRepositoryImpl implements GroupRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void save(Group group) {
        entityManager.persist(group);
    }

    @Override
    public Group edit(Group group) {
        entityManager.flush();
        return entityManager.merge(group);
    }

    @Override
    public void remove(Long id) {

        Optional<Group> group = findOne(id);
        if (group.isPresent())
            entityManager.remove(group.get());
    }

    @Override
    public Optional<Group> findOne(Long id) {
        Group group = entityManager.find(Group.class, id);
        return Optional.ofNullable(group);
    }

    @Override
    public List<Group> findAll() {
        TypedQuery<Group> query = entityManager.createQuery("select g from _group g", Group.class);
        return query.getResultList();
    }

    @Override
    public Optional<Group> findByName(String name) {
        TypedQuery<Group> query = entityManager.createQuery("select g from _group g where g.name=:name", Group.class);
        query.setParameter("name", name);
        List<Group> groups = query.getResultList();
        return groups.stream().findFirst();
    }
}

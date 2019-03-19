package ro.sda.repository;

import ro.sda.util.HibernateUtil;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractRepository<Entity extends Serializable> {


    public Entity createUpdate(Entity e) {

        try {
            em().getTransaction().begin();
            em().persist(e);
            em().getTransaction().commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            em().getTransaction().rollback();
        }
        return e;
    }


    public Entity read(long id) {

        return em().find(entityClass(), id);
    }

    public Entity read(String strId) {

        return em().find(entityClass(), strId);
    }

    public Entity read(Object objId) {

        return em().find(entityClass(), objId);
    }

    public List<Entity> readAll() {
        CriteriaBuilder builder = em().getCriteriaBuilder();
        Class<Entity> entityClass = entityClass();
        CriteriaQuery<Entity> query = builder.createQuery(entityClass);
        Root<Entity> root = query.from(entityClass);
        query.select(root);

        return em().createQuery(query).getResultList();
    }

    public boolean delete(Entity e) {

        boolean result = true;

        try {
            em().getTransaction().begin();
            em().remove(e);
            em().getTransaction().commit();
        } catch (PersistenceException pe) {
            pe.printStackTrace();
            result = false;
            em().getTransaction().rollback();
        }

        return result;
    }


    public <Entity extends Serializable> List<Entity> findAllBy(Class<Entity> entityClass, List<String> attributes, List<String> values) {

        List entities = null;

        CriteriaBuilder builder = em().getCriteriaBuilder();
        CriteriaQuery query = builder.createQuery();
        Root<Entity> root = query.from(entityClass); //=> "SELECT * from Entity"
        query.select(root);

        if (attributes != null && values != null && attributes.size() > 0 && values.size() > 0 &&
            attributes.size() == values.size()) {
            List<Predicate> predicates = new ArrayList<>();
            for (int i = 0; i < attributes.size(); i++) {
                String attr = attributes.get(i);
                String val = values.get(i);
                predicates.add(builder.equal(root.get(attr), val));
            }

            query.where( //=> " WHERE "
                    builder.and(
                            predicates.toArray(new Predicate[predicates.size()])
                    )
            );
        }

        entities = em().createQuery(query).getResultList();

        return entities;
    }


    protected EntityManager em() {
        return HibernateUtil.getEM();
    }

    public abstract Class<Entity> entityClass();

}

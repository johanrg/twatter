package service;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
public abstract class AbstractService<T> {
    private final Class<T> entityClass;

    public AbstractService(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void persist(T entity) {
        getEntityManager().persist(entity);
    }

    public void merge(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }

    public void refresh(Object id) {
        T entity = getEntityManager().find(entityClass, id);
        getEntityManager().refresh(entity);
    }

    public List<T> getAll() {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        criteriaQuery.select(criteriaQuery.from(entityClass));
        return getEntityManager().createQuery(criteriaQuery).getResultList();
    }

    public int count() {
        CriteriaQuery<T> criteriaQuery = getEntityManager().getCriteriaBuilder().createQuery(entityClass);
        Root<T> root = criteriaQuery.from(entityClass);
        criteriaQuery.select(getEntityManager().getCriteriaBuilder().count(root));
        Query query = getEntityManager().createQuery(criteriaQuery);
        return ((Long) query.getSingleResult()).intValue();
    }
}

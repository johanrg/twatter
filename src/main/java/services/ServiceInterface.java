package services;

import javax.persistence.EntityManager;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
public abstract class ServiceInterface<T> {
    private final Class<T> entityClass;

    public ServiceInterface(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    protected abstract EntityManager getEntityManager();

    public void save(T entity) {
        getEntityManager().persist(entity);
    }

    public void edit(T entity) {
        getEntityManager().merge(entity);
    }

    public void remove(T entity) {
        getEntityManager().remove(getEntityManager().merge(entity));
    }

    public T find(Object id) {
        return getEntityManager().find(entityClass, id);
    }
}

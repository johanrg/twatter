package service;

import entity.UserClass;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Stateless
public class UserClassService extends AbstractService<UserClass> {
    @PersistenceContext
    EntityManager entityManager;

    public UserClassService() {
        super(UserClass.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }

}

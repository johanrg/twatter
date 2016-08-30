package service;

import entity.Forum;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author Johan Gustafsson
 * @since 2016-08-24.
 */
@Stateless
public class ForumService extends AbstractService<Forum> {
    @PersistenceContext
    EntityManager entityManager;

    public ForumService() {
        super(Forum.class);
    }


    public Forum findByName(String name) {
        try {
            return getEntityManager().createNamedQuery("Forum.findByName", Forum.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }

}

package services;

import entities.Forum;

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

    public Forum createForum(String name) {
        Forum forum = new Forum();
        forum.setName(name);

        create(forum);
        return forum;
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

package service;

import entity.ForumThread;
import entity.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

/**
 * @author Johan Gustafsson
 * @since 2016-08-25.
 */
@Stateless
public class ForumThreadService extends AbstractService<ForumThread> {
    @PersistenceContext
    EntityManager entityManager;

    public ForumThreadService() {
        super(ForumThread.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

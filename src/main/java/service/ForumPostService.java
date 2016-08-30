package service;

import entity.ForumPost;
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
public class ForumPostService extends AbstractService<ForumPost> {
    @PersistenceContext
    EntityManager entityManager;

    public ForumPostService() {
        super(ForumPost.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

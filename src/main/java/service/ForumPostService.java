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

    public ForumPost newPost(User user, String message, Timestamp createdAt) {
        ForumPost forumPost = new ForumPost();
        forumPost.setUser(user);
        forumPost.setMessage(message);
        forumPost.setCreatedAt(createdAt);

        return forumPost;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

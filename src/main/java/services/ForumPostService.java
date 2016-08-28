package services;

import entities.ForumPost;
import entities.ForumThread;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.sql.Timestamp;
import java.util.List;

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

package services;

import entities.Post;
import entities.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;

/**
 * @author Johan Gustafsson
 * @since 2016-08-25.
 */
@Stateless
public class PostService extends AbstractService<Post> {
    @PersistenceContext
    EntityManager entityManager;

    public PostService() {
        super(Post.class);
    }

    public Post newPost(User user, String message, Timestamp createdAt) {
        Post post = new Post();
        post.setUser(user);
        post.setMessage(message);
        post.setCreatedAt(createdAt);

        return post;
    }

    @Override
    protected EntityManager getEntityManager() {
        return entityManager;
    }
}

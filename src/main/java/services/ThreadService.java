package services;

import entities.Forum;
import entities.Post;
import entities.User;
import entities.Thread;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 * @author Johan Gustafsson
 * @since 2016-08-25.
 */
@Stateless
public class ThreadService extends AbstractService<Thread> {
    @PersistenceContext
    EntityManager entityManager;

    public ThreadService() {
        super(Thread.class);
    }

    public entities.Thread newThread(User startedBy, String topic, Timestamp createdAt) {
        entities.Thread thread = new entities.Thread();
        thread.setStartedBy(startedBy);
        thread.setTopic(topic);
        thread.setCreatedAt(createdAt);
        thread.setPost(new ArrayList<>());

        return thread;
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

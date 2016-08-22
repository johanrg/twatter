package services;

import entities.User;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Named
@Stateless
public class UserService extends ServiceInterface<User> {
    @PersistenceContext
    private EntityManager entityManager;

    public UserService() {
        super(User.class);
    }

    public User createPerson(String name, String password) {
        User user = new User();
        user.setId(1);
        user.setName(name);
        user.setPassword(password);

        save(user);
        return user;
    }

    public User findByName(String name) {
        try {
            return (User) getEntityManager().createNamedQuery("User.findByName").setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByNameAndPassword(String name, String password) {
        try {
            return (User) getEntityManager()
                    .createNamedQuery("User.findByNameAndPassword")
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(User entity) {
        entityManager.persist(entity);
    }

    public String login() {
        return "index";
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

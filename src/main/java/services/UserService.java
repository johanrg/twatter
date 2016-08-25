package services;

import entities.User;
import entities.UserClass;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Stateless
public class UserService extends AbstractService<User> {
    @PersistenceContext
    private EntityManager entityManager;

    public UserService() {
        super(User.class);
    }

    public User newPerson(String name, String password, UserClass userClass) {
        User user = new User();
        user.setName(name);
        user.setPassword(password);
        user.setUserClass(userClass);

        return user;
    }

    public User findByName(String name) {
        try {
            return getEntityManager().createNamedQuery("User.findByName", User.class).setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public User findByNameAndPassword(String name, String password) {
        try {
            return getEntityManager()
                    .createNamedQuery("User.findByNameAndPassword", User.class)
                    .setParameter("name", name)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public EntityManager getEntityManager() {
        return entityManager;
    }
}

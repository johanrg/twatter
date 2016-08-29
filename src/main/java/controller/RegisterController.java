package controller;

import entity.User;
import entity.UserClass;
import service.UserClassService;
import service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.io.Serializable;


/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Named
@RequestScoped
public class RegisterController implements Serializable {
    @Inject
    private UserService userService;
    @Inject
    private UserClassService userClassService;

    @NotNull
    @Size(min = 1, max = 60)
    private String name;
    @NotNull
    @Size(min = 1, max = 45)
    private String password;

    public String submit() throws IOException {
        UserClass userClass;
        if (userService.count() == 0) {
            // First user is always an admin, hard coded id
            userClass = userClassService.find(1);
        } else {
            // Normal user
            userClass = userClassService.find(3);
        }

        if (userService.findByName(name) == null) {
            User user = userService.newPerson(name, password, userClass);
            userService.persist(user);
            return "thankyou?faces-redirect=true&username=" + name;
        } else {
            FacesContext.getCurrentInstance().addMessage("register:username", new FacesMessage("Username is already taken"));
            return null;
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


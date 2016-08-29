package controller;

import entity.User;
import service.UserService;

import javax.enterprise.context.SessionScoped;
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
@SessionScoped
public class LoginController implements Serializable {
    @Inject
    private UserService userService;
    private User user;

    @NotNull
    @Size(min = 1, max = 60)
    private String name;
    @NotNull
    @Size(min = 1, max = 45)
    private String password;

    private boolean loggedIn;
    private boolean admin;
    private boolean moderator;

    public String submit() throws IOException {
        user = userService.findByNameAndPassword(name, password);
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage("login:loginGroupPanel", new FacesMessage("Invalid username or password"));
            return null;
        } else {
            admin = user.getUserClass().getAdmin();
            moderator = user.getUserClass().getModerator();
            loggedIn = true;
            return "forum?faces-redirect=true";
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

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public boolean isAdmin() {
        return admin;
    }

    public boolean isModerator() {
        return moderator;
    }

    public void logout() throws IOException {
        admin = false;
        moderator = false;
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("forum.xhtml");
    }

    public User getUser() {
        return user;
    }
}



package controller;

import entities.User;
import services.UserService;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.IOException;
import java.io.Serializable;


/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Named
@SessionScoped
public class LoginController implements Serializable{
    @Inject
    UserService userService;

    private String name;
    private String password;
    private boolean loggedIn = false;
    private boolean admin;
    private boolean moderator;

    public String submit() throws IOException {
        User user = userService.findByNameAndPassword(name, password);
        if (user == null) {
            FacesContext.getCurrentInstance().addMessage("login:loginGroupPanel", new FacesMessage("Invalid username or password"));
            return null;
        } else {
            admin = user.getUserClass().getAdmin();
            moderator = user.getUserClass().getModerator();
            loggedIn = true;
            return "thankyou";
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

    public void logout() throws IOException {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
}


package controller;

import services.UserService;

import javax.enterprise.context.SessionScoped;
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
    private String status;
    private boolean loggedIn = false;

    public void submit(ActionEvent evt) throws IOException {
        if (userService.findByNameAndPassword(name, password) == null) {
            status = "Incorrect username or password";
        } else {
            status = "";
            loggedIn = true;
            FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
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

    public String getStatus() {
        return status;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }


    public void logout() throws IOException {
        loggedIn = false;
        FacesContext.getCurrentInstance().getExternalContext().redirect("login.xhtml");
    }
}


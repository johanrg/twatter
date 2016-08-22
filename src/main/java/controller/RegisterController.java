package controller;

import services.UserService;

import javax.enterprise.context.ConversationScoped;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIColumn;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.validator.ValidatorException;
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
public class RegisterController implements Serializable {
    @Inject
    private UserService userService;

    private String name;
    private String password;
    private String confirmPassword;

    public String submit() throws IOException {
        if (userService.findByName(name) == null) {
            userService.createPerson(name, password);
            return "login";
        } else {
            FacesContext.getCurrentInstance().addMessage("register:username", new FacesMessage("Username is already taken"));
            return "register";
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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}


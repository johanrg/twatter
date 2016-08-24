package controller;


import entities.Forum;
import services.ForumService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-24.
 */
@Named
@RequestScoped
public class AdminController implements Serializable {
    @Inject
    private ForumService forumService;
    private String forumName;
    private int forumId;

    public void deleteForum(int id) {
        Forum forum = forumService.find(id);
        forumService.remove(forum);
    }

    public String addForum() {
        if (forumService.findByName(forumName) == null) {
            forumService.createForum(forumName);
            return "admin_forum?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage("newForum:forumName", new FacesMessage("There is already a forum with that name."));
            return null;
        }
    }

    public String editForum() {
        Forum forum = forumService.find(forumId);
        if (forum != null) {
            forum.setName(forumName);
            forumService.update(forum);
            return "admin_forum?faces-redirect=true";
        }
        FacesContext.getCurrentInstance().addMessage("editForum:forumName", new FacesMessage("The forum you are editing does not exits."));
        return null;
    }

    public String getForumName() {
        return forumName;
    }

    public void setForumName(String forumName) {
        this.forumName = forumName;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
        Forum forum = forumService.find(forumId);
        forumName = forum.getName();
    }

    public ForumService getForumService() {
        return forumService;
    }
}

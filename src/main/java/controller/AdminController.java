package controller;


import com.sun.org.glassfish.gmbal.IncludeSubclass;
import entity.Forum;
import entity.ForumPost;
import entity.ForumThread;
import service.ForumPostService;
import service.ForumService;
import service.ForumThreadService;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * @author Johan Gustafsson
 * @since 2016-08-24.
 */
@Named
@RequestScoped
public class AdminController implements Serializable {
    @Inject
    private ForumService forumService;
    @Inject
    private ForumThreadService forumThreadService;
    @Inject
    private ForumPostService forumPostService;

    @NotNull
    @Size(min = 1, max = 50)
    private String forumName;
    private int forumId;

    public void deleteForum(int id) {
        Forum forum = forumService.find(id);
        forumService.remove(forum);
    }

    public void deletePosts(ForumPost forumPost) {
        if (forumPost.getReplies().size() > 0) {
            for (ForumPost fp : forumPost.getReplies()) {
                deletePosts(fp);
            }
        }
        forumPostService.remove(forumPost);
    }

    public String addForum() {
        if (forumService.findByName(forumName) == null) {
            Forum forum = new Forum(forumName);
            forumService.persist(forum);
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
            forumService.merge(forum);
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

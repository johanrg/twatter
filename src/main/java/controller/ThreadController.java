package controller;

import entities.Forum;
import entities.Thread;
import entities.User;
import services.ForumService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;
import java.util.List;

/**
 * @author johan
 * @since 2016-08-24.
 */
@Named
@RequestScoped
public class ThreadController {
    @Inject
    private ForumService forumService;
    private Forum forum;

    private int forumId;

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public List<Thread> getThreadList() {
        forum = forumService.find(forumId);
        if (forum != null) {
            return forum.getThreads();
        } else {
            return null;
        }
    }

    public Forum getForum() {
        return forum;
    }
}

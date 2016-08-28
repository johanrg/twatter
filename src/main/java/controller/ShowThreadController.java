package controller;

import entities.ForumPost;
import entities.ForumThread;
import services.ForumPostService;
import services.ForumThreadService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author johan
 * @since 2016-08-27.
 */
@Named
@RequestScoped
public class ShowThreadController {
    @Inject
    private ForumThreadService forumThreadService;
    @Inject
    private ForumPostService forumPostService;

    private int threadId;

    public List<ForumPost> getPostList() {
        if (threadId > 0) {
            ForumThread forumThread = forumThreadService.find(threadId);
            if (forumThread != null) {
                return forumThread.getForumPosts();
            }
        }
        return null;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }
}

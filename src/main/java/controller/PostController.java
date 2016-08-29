package controller;

import entity.Forum;
import entity.ForumPost;
import entity.ForumThread;
import entity.User;
import service.ForumService;
import service.ForumPostService;
import service.ForumThreadService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.TransactionScoped;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Johan Gustafsson
 * @since 2016-08-25.
 */
@Named
@RequestScoped
public class PostController implements Serializable {
    @Inject
    LoginController loginController;
    @Inject
    ForumService forumService;
    @Inject
    ForumThreadService forumThreadService;
    @Inject
    ForumPostService forumPostService;

    private int forumId;
    private int threadId;
    private int postId;
    private int editId;

    @NotNull
    @Size(min = 1, max = 60)
    private String topic;
    @NotNull
    @Size(min = 1, max = 64000)
    private String message;

    public String createNewTopic() {
        User user = loginController.getUser();
        Forum forum = forumService.find(forumId);
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTime = new Timestamp(now.getTime());

        ForumPost forumPost = forumPostService.newPost(user, message, currentTime);
        ForumThread forumThread = forumThreadService.newThread(user, topic, currentTime);

        forumPost.setForumThread(forumThread);
        forumThread.addForumPost(forumPost);
        forumThread.setForum(forum);
        forum.addForumThread(forumThread);
        forumService.merge(forum);

        return "forumdisplay?faces-redirect=true&forumId=" + forumId;
    }

    public String replyToPost(int id) {
        User user = loginController.getUser();
        Calendar calendar = Calendar.getInstance();
        Date now = calendar.getTime();
        Timestamp currentTime = new Timestamp(now.getTime());

        ForumThread forumThread = forumThreadService.find(threadId);
        ForumPost replyToPost = forumPostService.find(postId);
        ForumPost forumPost = forumPostService.newPost(user, message, currentTime);

        forumPost.setReplyTo(replyToPost);
        forumPost.setForumThread(forumThread);
        forumPostService.persist(forumPost);

        replyToPost.addReply(forumPost);
        forumPostService.merge(replyToPost);

        forumThread.addForumPost(forumPost);
        forumThread.setPostCount(forumThread.getPostCount() + 1);
        forumThreadService.merge(forumThread);

        return "showthread?faces-redirect=true&threadId=" + threadId;
    }

    public String removePost(int id) {
        ForumPost forumPost = forumPostService.find(id);
        if (forumPost == null) return null;
        if (forumPost.numberOfReplies() > 0) {
            forumPost.setMessage("<DELETED>");
            forumPostService.merge(forumPost);
        } else {
            ForumThread forumThread = forumPost.getForumThread();

            forumThread.removeForumPost(forumPost);
            forumThread.setPostCount(forumThread.getPostCount() - 1);

            ForumPost parent = forumPost.getReplyTo();
            if (parent != null) {
                parent.removeReply(forumPost);
                forumPostService.merge(parent);
            }
            forumPostService.remove(forumPost);
            if (forumThread.getNumberOfForumPosts() == 0) {
                Forum forum = forumThread.getForum();
                forum.removeForumThread(forumThread);
                forumThreadService.remove(forumThread);
                forumService.merge(forum);
                return "forum?faces-redirect=true";
            } else {
                forumThreadService.merge(forumThread);
            }
        }
        return "showthread?faces-redirect=true&threadId=" + threadId;
    }

    public String editPost() {
        ForumPost forumPost = forumPostService.find(editId);
        forumPost.setMessage(message);
        forumPostService.merge(forumPost);

        return "showthread?faces-redirect=true&threadId=" + threadId;
    }

    public ForumPost getPostById(int id) {
        return forumPostService.find(id);
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
    }

    public int getThreadId() {
        return threadId;
    }

    public void setThreadId(int threadId) {
        this.threadId = threadId;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public void setEditId(int editId) {
        this.editId = editId;
        if (editId != 0) {
            ForumPost forumPost = forumPostService.find(editId);
            if (forumPost != null) {
                message = forumPost.getMessage();
            }
        }
    }

    public int getEditId() {
        return this.editId;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

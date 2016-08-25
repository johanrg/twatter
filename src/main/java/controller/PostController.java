package controller;

import entities.Forum;
import entities.Post;
import entities.User;
import services.ForumService;
import services.PostService;
import services.ThreadService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
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
    ThreadService threadService;
    @Inject
    PostService postService;

    private int forumId;
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

        Post post = postService.newPost(user, message, currentTime);
        entities.Thread thread = threadService.newThread(user, topic, currentTime);
        thread.getPost().add(post);
        forum.getThreads().add(thread);
        forumService.update(forum);

        return "forumdisplay?faces-redirect=true&forumId=" + forumId;
    }

    public int getForumId() {
        return forumId;
    }

    public void setForumId(int forumId) {
        this.forumId = forumId;
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

package entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
public class ForumPost {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER", referencedColumnName = "ID")
    private User user;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FORUMTHREAD", referencedColumnName = "ID")
    private ForumThread forumThread;

    @Column(columnDefinition = "TEXT")
    private String message;

    @Column(nullable = false)
    private Timestamp createdAt;

    @ManyToOne(optional = true)
    private ForumPost replyTo;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            orphanRemoval = true, mappedBy = "replyTo")
    private List<ForumPost> replies = new ArrayList<>();

    public ForumPost() {}

    public ForumPost(User user, String message, Timestamp createdAt) {
        this.user = user;
        this.message = message;
        this.createdAt = createdAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ForumThread getForumThread() {
        return forumThread;
    }

    public void setForumThread(ForumThread forumThreads) {
        this.forumThread = forumThreads;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public List<ForumPost> getReplies() {
        return replies;
    }

    public void setReplies(List<ForumPost> replies) {
        this.replies = replies;
    }

    public void addReply(ForumPost forumPost) {
        if (forumPost != null) {
            if (replies == null) {
                replies = new ArrayList<>();
            }
            replies.add(forumPost);
        }
    }

    public void removeReply(ForumPost forumPost) {
        if (forumPost != null && replies != null) {
            int index = replies.indexOf(forumPost);
            if (index != -1) {
                replies.remove(index);
            }
        }
    }

    public int numberOfReplies() {
        if (replies != null) {
            return replies.size();
        } else {
            return 0;
        }
    }

    public ForumPost getReplyTo() {
        return replyTo;
    }

    public void setReplyTo(ForumPost replyTo) {
        this.replyTo = replyTo;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id").append(id);
        s.append(", createdAd:").append(createdAt);
        s.append(", message:").append(message);
        if (forumThread != null) s.append(", forumThread:").append(forumThread.getTopic());
        if (replyTo != null) s.append(", replyTo:").append(replyTo.getId());
        if (user != null) s.append(", user:").append(user.getName());
        return s.toString();
    }
}

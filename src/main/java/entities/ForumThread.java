package entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
public class ForumThread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "FORUM", referencedColumnName = "ID")
    private Forum forum;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USER", referencedColumnName = "ID")
    private User startedBy;

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "forumThread")
    @OrderBy("createdAt ASC")
    private List<ForumPost> forumPosts;

    @Column(length = 60, nullable = false)
    private String topic;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Integer postCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Forum getForum() {
        return forum;
    }

    public void setForum(Forum forum) {
        this.forum = forum;
    }

    public User getStartedBy() {
        return startedBy;
    }

    public void setStartedBy(User startedBy) {
        this.startedBy = startedBy;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public void addForumPost(ForumPost forumPost) {
        if (forumPost != null) {
            if (forumPosts == null) {
                forumPosts = new ArrayList<>();
            }
            forumPosts.add(forumPost);
        }
    }

    public void removeForumPost(ForumPost forumPost) {
        if (forumPost != null && forumPosts != null) {
            int index = forumPosts.indexOf(forumPost);
            if (index != -1) {
                forumPosts.remove(index);
            }
        }
    }

    public List<ForumPost> getForumPosts() {
        return forumPosts;
    }

    public Integer getPostCount() {
        return postCount;
    }

    public void setPostCount(Integer postCount) {
        this.postCount = postCount;
    }

    public int getNumberOfForumPosts() {
        return forumPosts.size();
    }
    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id: ").append(id);
        s.append(", createdAt:").append(createdAt);
        s.append(", topic:").append(topic);
        if (forum != null) s.append(", forum").append(forum.getName());
        if (startedBy != null) s.append(", startedBy").append(startedBy.getName());
        return s.toString();
    }
}

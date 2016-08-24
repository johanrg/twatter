package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
public class Thread {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Forum forum;

    @ManyToOne
    private User startedBy;

    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private String topic;

    @Column(nullable = false)
    private Timestamp createdAt;

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
}

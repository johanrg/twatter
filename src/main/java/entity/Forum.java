package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Forum.findByName", query = "SELECT f FROM Forum f WHERE f.name = :name"),
})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE},
            orphanRemoval = true, mappedBy = "forum")
    private List<ForumThread> forumThreads;

    @Column(length = 50, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void addForumThread(ForumThread forumThread) {
        if (forumThread != null) {
            if (forumThreads == null) {
                forumThreads = new ArrayList<>();
            }
            forumThreads.add(forumThread);
        }
    }

    public void removeForumThread(ForumThread forumThread) {
        if (forumThread != null && forumThreads != null) {
            int index = forumThreads.indexOf(forumThread);
            if (index != -1) {
                forumThreads.remove(index);
            }
        }
    }

    public List<ForumThread> getForumThreads() {
        return forumThreads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id:").append(id);
        s.append(", name:").append(name);
        return s.toString();
    }
}

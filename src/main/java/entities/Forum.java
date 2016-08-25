package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "Forum.findByName", query = "SELECT f FROM Forum f WHERE f.name = :name"),
})
@Table(uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class Forum {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "forum", cascade = CascadeType.PERSIST)
    private List<entities.Thread> threads;

    @Column(length = 50, nullable = false)
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<entities.Thread> getThreads() {
        return threads;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

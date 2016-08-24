package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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

    @NotNull
    @Size(min = 1, max = 50)
    @Column(columnDefinition = "VARCHAR(50) NOT NULL")
    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

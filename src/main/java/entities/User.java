package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findAll", query = "SELECT u FROM User u"),
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
})
@Table(name = "user", uniqueConstraints={@UniqueConstraint(columnNames = {"name"})})
public class User {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "name", columnDefinition = "VARCHAR(60) NOT NULL")
    private String name;

    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "password", columnDefinition = "VARCHAR(45) NOT NULL", unique = true)
    private String password;

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

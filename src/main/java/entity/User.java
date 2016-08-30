package entity;

import javax.persistence.*;

/**
 * @author Johan Gustafsson
 * @since 2016-08-17.
 */
@Entity
@NamedQueries({
        @NamedQuery(name = "User.findByName", query = "SELECT u FROM User u WHERE u.name = :name"),
        @NamedQuery(name = "User.findByNameAndPassword", query = "SELECT u FROM User u WHERE u.name = :name AND u.password = :password")
})
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "USERCLASS", referencedColumnName = "ID")
    private UserClass userClass;

    @Column(length = 60, nullable = false)
    private String name;

    @Column(length = 45, nullable = false)
    private String password;

    public User() {}

    public User(String name, String password, UserClass userClass) {
        this.name = name;
        this.password = password;
        this.userClass = userClass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UserClass getUserClass() {
        return userClass;
    }

    public void setUserClass(UserClass userClass) {
        this.userClass = userClass;
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

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("id: ").append(id);
        s.append(", name:").append(name);
        s.append(", password:").append(password);
        if (userClass != null) s.append(", userClass:").append(userClass.getName());
        return s.toString();
    }
}

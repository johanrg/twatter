package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
public class UserClass {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(columnDefinition = "VARCHAR(30) NOT NULL")
    //@NotNull
    //@Size(min = 1, max = 30)
    private String name;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean admin;

    @Column(columnDefinition = "BIT(1) DEFAULT 0")
    private Boolean moderator;

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

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }

    public Boolean getModerator() {
        return moderator;
    }

    public void setModerator(Boolean moderator) {
        this.moderator = moderator;
    }
}

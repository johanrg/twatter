package entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Johan Gustafsson
 * @since 2016-08-23.
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @ManyToOne
    private User user;

    @ManyToOne
    private Thread thread;

    @Column(columnDefinition = "TEXT")
    private String message;
}

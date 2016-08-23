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

    @ManyToOne
    @NotNull
    private Forum forum;

    @ManyToOne
    @NotNull
    private User startedBy;

    @NotNull
    @Size(min = 1, max = 60)
    @Column(columnDefinition = "VARCHAR(60) NOT NULL")
    private String topic;

    @NotNull
    @Column(nullable = false)
    private Timestamp createdAt;
}

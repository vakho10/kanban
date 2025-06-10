package ge.edu.sangu.kanban.db;

import jakarta.persistence.*;
import lombok.ToString;

@Entity
@Table(name = "authorities", uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "authority"})})
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "username", nullable = false)
    @ToString.Exclude  // Prevents recursion issues when printing
    private User user;

    @Column(length = 50, nullable = false)
    private String authority;
}

package ge.edu.sangu.kanban.db;

import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.ToString;

import java.util.Set;

@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(length = 50, nullable = false, unique = true)
    private String username;

    @Column(length = 500, nullable = false) // Encoded password will be longer than the real one! keep in mind.
    private String password;

    @Column(nullable = false)
    private boolean enabled;

    @ToString.Exclude
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities;

//    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Post> posts; // User's posts
}

package ge.edu.sangu.kanban;

import ge.edu.sangu.kanban.board.Board;
import ge.edu.sangu.kanban.board.BoardRepository;
import ge.edu.sangu.kanban.card.Card;
import ge.edu.sangu.kanban.pillar.Pillar;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class SetupDummyData {

    private final JdbcUserDetailsManager userDetailsManager;
    private final PasswordEncoder passwordEncoder;

    private final BoardRepository boardRepository;

    @EventListener(ApplicationReadyEvent.class)
    public void onApplicationReady() {
        var users = List.of(
                // Administrator
                User.withUsername("admin")
                        .password(passwordEncoder.encode("admin"))
                        .roles("USER", "ADMIN")
                        .build(),
                // Sample users
                User.withUsername("vakho")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("gio")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("dato")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("mari")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build(),
                User.withUsername("bob")
                        .password(passwordEncoder.encode("password"))
                        .roles("USER")
                        .build()
        );

        // Save these users in database
        users.stream()
                .filter(user -> !userDetailsManager.userExists(user.getUsername()))
                .forEach(userDetailsManager::createUser);

        generateDummyBoards();
    }

    private void generateDummyBoards() {
        Board board = new Board();
        board.setTitle("Superb Kanban Project");
        board.setDescription("A demo Kanban board with pillars and tasks to showcase cascading saves.");

        List<Pillar> pillars = new ArrayList<>();

        // Backlog Pillar
        Pillar backlog = new Pillar();
        backlog.setColor("text-bg-danger");
        backlog.setTitle("Backlog");
        backlog.setDescription("Tasks waiting to be picked up.");
        backlog.setBoard(board);

        List<Card> backlogCards = List.of(
                new Card(null, "Setup Project", "Initialize the Spring Boot project.", backlog),
                new Card(null, "Design Database", "Create the ER diagram and define tables.", backlog)
        );
        backlog.setCards(backlogCards);
        pillars.add(backlog);

        // In Progress Pillar
        Pillar inProgress = new Pillar();
        inProgress.setColor("text-bg-warning");
        inProgress.setTitle("In Progress");
        inProgress.setDescription("Tasks currently being worked on.");
        inProgress.setBoard(board);

        List<Card> inProgressCards = List.of(
                new Card(null, "Implement Authentication", "Add login and signup features.", inProgress),
                new Card(null, "Develop API", "Build REST endpoints for tasks.", inProgress)
        );
        inProgress.setCards(inProgressCards);
        pillars.add(inProgress);

        // Review Pillar
        Pillar review = new Pillar();
        review.setColor("text-bg-primary");
        review.setTitle("Review");
        review.setDescription("Tasks pending code review.");
        review.setBoard(board);

        List<Card> reviewCards = List.of(
                new Card(null, "Fix UI Bugs", "Resolve visual issues in the dashboard.", review),
                new Card(null, "Write Tests", "Add unit and integration tests.", review)
        );
        review.setCards(reviewCards);
        pillars.add(review);

        // Done Pillar
        Pillar done = new Pillar();
        done.setColor("text-bg-success");
        done.setTitle("Done");
        done.setDescription("Completed tasks.");
        done.setBoard(board);

        List<Card> doneCards = List.of(
                new Card(null, "Setup GitHub Repo", "Created and pushed initial code to GitHub.", done),
                new Card(null, "Configure CI/CD", "CI/CD pipeline is up and running.", done)
        );
        done.setCards(doneCards);
        pillars.add(done);

        // Set pillars to board and save
        board.setPillars(pillars);
        boardRepository.save(board);

    }
}

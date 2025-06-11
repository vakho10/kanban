package ge.edu.sangu.kanban.pillar;

import ge.edu.sangu.kanban.board.Board;
import ge.edu.sangu.kanban.card.Card;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pillar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String color;

    @Column(nullable = false)
    private String title;

    private String description;

    @ToString.Exclude
    @OneToMany(mappedBy = "pillar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Card> cards;

    @ManyToOne
    @JoinColumn(name = "board_id", nullable = false)
    private Board board;
}

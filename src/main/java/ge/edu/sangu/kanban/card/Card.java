package ge.edu.sangu.kanban.card;

import ge.edu.sangu.kanban.pillar.Pillar;
import jakarta.persistence.*;
import jakarta.persistence.Column;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    private String description;

    @ManyToOne
    @JoinColumn(name = "pillar_id", nullable = false)
    private Pillar pillar;
}

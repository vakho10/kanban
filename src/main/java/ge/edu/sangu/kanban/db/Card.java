package ge.edu.sangu.kanban.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Card {
    private Long id;
    private String title;
    private String description;
}

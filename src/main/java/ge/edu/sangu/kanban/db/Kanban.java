package ge.edu.sangu.kanban.db;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Kanban {
    private Long id;
    private String title;
    private String description;
    private List<Column> columns;
}

package ge.edu.sangu.kanban.board;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardEditForm {

    @NotNull(message = "Id is required")
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 50, message = "Title should be less than 50 characters long")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 200, message = "Description should be less than 200 characters long")
    private String description;
}

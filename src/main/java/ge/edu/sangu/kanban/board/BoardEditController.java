package ge.edu.sangu.kanban.board;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class BoardEditController {

    private final BoardService boardService;

    @GetMapping("/board/edit/{id}")
    public String editBoard(Model model, @PathVariable Long id) {
        if (!model.containsAttribute("boardEditForm")) {
            Board board = boardService.getBoardById(id);
            model.addAttribute("boardEditForm", new BoardEditForm(board.getId(), board.getTitle(), board.getDescription()));
        }
        return "boardEditForm";
    }

    @PostMapping("/board/edit")
    public String editBoard(@ModelAttribute("boardEditForm") @Valid BoardEditForm boardEditForm,
                        BindingResult result) {
        if (result.hasErrors()) {
            return "boardEditForm";
        }

        // Update board information
        boardService.updateBoard(boardEditForm);
        return String.format("redirect:/board/edit/%d?success", boardEditForm.getId());
    }
}

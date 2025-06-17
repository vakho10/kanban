package ge.edu.sangu.kanban.controller;

import ge.edu.sangu.kanban.board.Board;
import ge.edu.sangu.kanban.board.BoardRepository;
import ge.edu.sangu.kanban.board.BoardEditForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final BoardRepository boardRepository;

    @GetMapping({"/", "/home", "/index", "/index.html"})
    public String index(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        List<BoardEditForm> boardEditForms = boards.parallelStream()
                .map(b -> new BoardEditForm(b.getId(), b.getTitle(), b.getDescription()))
                .toList();
        model.addAttribute("boardEditForms", boardEditForms);
        return "index";
    }
}

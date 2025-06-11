package ge.edu.sangu.kanban.controller;

import ge.edu.sangu.kanban.board.Board;
import ge.edu.sangu.kanban.board.BoardRepository;
import ge.edu.sangu.kanban.card.Card;
import ge.edu.sangu.kanban.pillar.Pillar;
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
        model.addAttribute("board", boardRepository.findAll().getFirst());
        return "index";
    }
}

package ge.edu.sangu.kanban.controller;

import ge.edu.sangu.kanban.db.Card;
import ge.edu.sangu.kanban.db.Column;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @GetMapping({"/", "/home", "/index", "/index.html"})
    public String index(Model model) {
        model.addAttribute("columns", List.of(
                new Column(
                        1L,
                        "text-bg-danger",
                        "Backlog", "What you can pick.",
                        List.of(
                                new Card(1L, "Buy Milk", "Buy a soy milk from supermarket."),
                                new Card(2L, "Check ATM Balance", "Check father's card balance.")
                        )
                ),
                new Column(
                        2L,
                        "text-bg-warning",
                        "Doing", "What's in progress.",
                        List.of(
                                new Card(3L, "Buy Milk", "Buy a soy milk from supermarket."),
                                new Card(4L, "Check ATM Balance", "Check father's card balance.")
                        )
                ),
                new Column(
                        3L,
                        "text-bg-success",
                        "Review", "What's being reviewed.",
                        List.of(
                                new Card(5L, "Buy Milk", "Buy a soy milk from supermarket."),
                                new Card(6L, "Check ATM Balance", "Check father's card balance.")
                        )
                ),
                new Column(
                        4L,
                        "text-bg-info",
                        "Done", "What is finished.",
                        List.of(
                                new Card(7L, "Buy Milk", "Buy a soy milk from supermarket."),
                                new Card(8L, "Check ATM Balance", "Check father's card balance.")
                        )
                )
        ));
        return "index";
    }
}

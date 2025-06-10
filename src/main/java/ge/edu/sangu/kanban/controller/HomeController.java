package ge.edu.sangu.kanban.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping({"/", "/home", "/index", "/index.html"})
    public String index() {
        return "index";
    }
}

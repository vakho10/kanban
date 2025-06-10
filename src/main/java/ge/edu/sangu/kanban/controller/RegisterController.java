package ge.edu.sangu.kanban.controller;

import ge.edu.sangu.kanban.controller.form.RegisterForm;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserDetailsManager userDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerForm", new RegisterForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute("registerForm") @Valid RegisterForm registerForm,
                                 BindingResult result) {
        if (result.hasErrors()) {
            return "register";
        }

        if (userDetailsManager.userExists(registerForm.getUsername())) {
            result.rejectValue("username", "error.user", "Username is already taken.");
            return "register";
        }

        // Register new username
        userDetailsManager.createUser(
                User.builder()
                        .username(registerForm.getUsername())
                        .password(passwordEncoder.encode(registerForm.getPassword()))
                        .roles("USER")
                        .build()
        );
        return "redirect:/login?success";
    }
}

package ge.edu.sangu.kanban.controller.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class RegisterForm {

    @NotBlank(message = "Username is required")
    @Size(max = 50, message = "Username should be less than 50 characters long")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(max = 50, message = "Password should be less than 50 characters long")
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

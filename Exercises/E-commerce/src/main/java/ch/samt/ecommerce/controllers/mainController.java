package ch.samt.ecommerce.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class mainController {

    @GetMapping("/")
    public String homePage(Model model, Authentication authentication) {
        return "homePage";
    }

    @GetMapping("/login")
    public String login() {
        return "loginPage";
    }

}

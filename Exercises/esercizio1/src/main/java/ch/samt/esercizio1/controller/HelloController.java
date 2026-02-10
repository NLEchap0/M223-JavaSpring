package ch.samt.esercizio1.controller;

import ch.samt.esercizio1.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HelloController {
    List<User> users = new ArrayList<User>();

    @GetMapping("/")
    public String hello() {
        return "hello";
    }

    @GetMapping("/{name}")
    public String hello(@PathVariable(value = "name", required = false) String name) {
        return "hello";
    }

    @GetMapping("/user/insert")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/user/insert")
    public String addUser(@ModelAttribute User user, Model model) {
        users.add(user);
        model.addAttribute("users", users);
        return "addUser";
    }


}

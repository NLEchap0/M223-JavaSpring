package ch.samt.customers.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MealGroupsController {

    @GetMapping("/customers/mealgroups")
    public String mealGroups(Model model) {
        return "mealGroups";
    }
}

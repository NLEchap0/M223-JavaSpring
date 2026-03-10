package ch.samt.clockcollection.controllers;

import ch.samt.clockcollection.models.Clock;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

@Controller
public class IndexController {
    private final List<Clock> clocks = new ArrayList<>(
            Arrays.asList(
                    new Clock(1L, "Rolex", "Platinum", 10023),
                    new Clock(2L, "Rolex", "Diamond", 50241),
                    new Clock(3L, "Festina", "Chrono", 200)
            )
    );

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("name", "Bryan");
        return "redirect:/clocks";
    }

    @GetMapping("/clocks")
    public String home(Model model) {
        model.addAttribute("name", "Bryan");
        return "index";
    }

    @GetMapping("/clocks/list")
    public String listClocks(@RequestParam(value = "id", required = false) Long id, Model model) {
        if (id == null) {
            model.addAttribute("clocks", clocks);
        }else{
            Clock clock = clocks.stream()
                    .filter(c -> c.getId().equals(id))
                    .findFirst()
                    .orElse(null);
            model.addAttribute("clocks", clock);
        }
        return "listClocks";
    }

    @GetMapping("/clocks/list/{brand}")
    public String listClocks(@PathVariable String brand, Model model) {
        if (brand == null) {
            model.addAttribute("clocks", clocks);
        }else{
            Stream<Clock> clock = clocks.stream()
                    .filter(c -> c.getBrand().equals(brand));
            model.addAttribute("clocks", clock);
        }
        return "listClocks";
    }

    @GetMapping("/clocks/add")
    public String addClock(Model model) {
        model.addAttribute("clock", new Clock());
        return "addClock";
    }

    @PostMapping("/clocks/saveClock")
    public String addCustomer(@Valid Clock clock, Errors errors) {
        if(errors.hasErrors()) {
            return "addClock";
        }
        clocks.add(clock);
        return "redirect:/clocks/list";
    }
}

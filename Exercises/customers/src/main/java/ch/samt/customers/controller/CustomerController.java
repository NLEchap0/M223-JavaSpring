package ch.samt.customers.controller;

import ch.samt.customers.model.Customer;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class CustomerController {
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("customer", new Customer());
        return "home";
    }

    private final List<Customer> customers = new ArrayList<>(
            Arrays.asList(
                    new Customer(1L, "Bryan", "Ciaponi", 104),
                    new Customer(2L, "Riley", "Bianchi", 69),
                    new Customer(3L, "Nicola", "Capelli", 67)
            )
    );

    @GetMapping("/loadCustomers")
    public String loadCustomers(Model model,
                                @RequestParam(value = "id",
                                        required = false) Long customerId) {
        if(customerId == null) {
            model.addAttribute("customers", customers);
        } else {
            Customer customer = customers.stream()
                    .filter(c -> c.getId().equals(customerId))
                    .findFirst().orElse(null);
            model.addAttribute("customers", customer);
        }
        return "customerList";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid Customer customer, Errors errors) {
        if(errors.hasErrors()) {
            return "customerList";
        }
        customers.add(customer);
        return "addCustomer";
    }
}

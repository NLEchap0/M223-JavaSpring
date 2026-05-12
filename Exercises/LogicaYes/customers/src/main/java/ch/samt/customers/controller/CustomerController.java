package ch.samt.customers.controller;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.model.Customer;
import ch.samt.customers.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Controller
public class CustomerController {

    private CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("customer", new Customer());
        return "home";
    }

    @GetMapping("/loadCustomers")
    public String loadCustomers(Model model,
                                @RequestParam(value = "id",
                                        required = false) Long customerId) {
        if(customerId == null) {
            model.addAttribute("customers", customerService.getAllUsers());
        } else {
            model.addAttribute("customers", customerService.getUsersById(customerId));

        }
        return "customerList";
    }

    @GetMapping("/loadCustomers/{surnameToFilter}")
    public String loadInsertPage(@PathVariable String surnameToFilter, Model model) {
        model.addAttribute("customers", customerService.getUsersBySurname(surnameToFilter));
        return "customerList";
    }

    @PostMapping("/")
    public String addCustomer(@Valid Customer customer, Errors errors) {
        if(errors.hasErrors()) {
            return "home";
        }
        customerService.addCustomer(customer);
        return "redirect:/loadCustomers";
    }

    @GetMapping("/edit/{customerId}")
    public String loadEditPage(@PathVariable Long customerId, @ModelAttribute Customer customer, Model model) {
        Customer customerToEdit = customerService.getUsersById(customerId);
        model.addAttribute("customer", customerToEdit);
        return "home";
    }

    @PostMapping("/edit/{customerId}")
    public String updateCustomer(@Valid Customer customer, Errors errors, @PathVariable Long customerId) {
        customer.setId(customerId);
        if(errors.hasErrors()) {
            return "home";
        }
        customerService.save(customer);
        return "redirect:/loadCustomers";
    }
}

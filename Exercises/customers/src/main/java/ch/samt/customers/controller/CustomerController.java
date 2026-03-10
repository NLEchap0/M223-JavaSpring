package ch.samt.customers.controller;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
public class CustomerController{
    private CustomerRepository customerRepository;

    @Autowired
    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
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
            model.addAttribute("customers", customerRepository.findAll());
        } else {
            customerRepository.findById(customerId).ifPresentOrElse(
                    c -> model.addAttribute("customers", List.of(c)),
                    () -> model.addAttribute("customers", Collections.emptyList())
            );
        }
        return "customerList";
    }

    @GetMapping("/loadCustomers/{surnameToFilter}")
    public String loadCustomersSurname(Model model, @PathVariable String surnameToFilter) {
        List<Customer> filteredCustomers = customerRepository.findBySurname(surnameToFilter);

        model.addAttribute("customers", filteredCustomers);

        return "customerList";
    }

    @PostMapping("/addCustomer")
    public String addCustomer(@Valid Customer customer, Errors errors) {
        if(errors.hasErrors()) {
            return "home";
        }
        customerRepository.save(customer);

        return "redirect:/loadCustomers";
    }
}

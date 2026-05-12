package ch.samt.customers.service;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.model.Customer;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;

import java.util.List;

@Service
public class CustomerService {

    private CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllUsers() {
        return (List<Customer>) customerRepository.findAll();
    }

    public Customer getUsersById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getUsersBySurname(String surname) {
        return customerRepository.findBySurnameIgnoreCase(surname);
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void save(Customer customer) {
        customerRepository.save(customer);
    }
}

package ch.samt.customers.service;

import ch.samt.customers.data.CustomerRepository;
import ch.samt.customers.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Optional<Customer> getCustomerById(Long id) {
        return customerRepository.findById(id);
    }

    public List<Customer> getCustomersBySurname(String surname) {
        return customerRepository.findBySurnameIgnoreCase(surname);
    }

    public List<Customer> getCustomersByCity(String city) {
        return customerRepository.findByCityIgnoreCase(city);
    }

    public void saveCustomer(Customer customer) {
        customerRepository.save(customer);
    }
}
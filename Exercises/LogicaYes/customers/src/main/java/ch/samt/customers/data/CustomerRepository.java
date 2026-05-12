package ch.samt.customers.data;

import ch.samt.customers.model.Customer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository
        extends CrudRepository<Customer, Long>{

//@Query("SELECT * FROM  customer WHERE LOWER(surname) = LOWER(:surname)")
List<Customer> findBySurnameIgnoreCase(String surname);

}

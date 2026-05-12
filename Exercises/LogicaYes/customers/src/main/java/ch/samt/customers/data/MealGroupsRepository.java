package ch.samt.customers.data;

import ch.samt.customers.model.Reservation;
import org.springframework.data.repository.CrudRepository;

public interface MealGroupsRepository
        extends CrudRepository<Reservation, Long> {
}

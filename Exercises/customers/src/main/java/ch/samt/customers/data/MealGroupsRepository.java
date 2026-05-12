package ch.samt.customers.data;

import ch.samt.customers.model.MealGroup;
import org.springframework.data.repository.CrudRepository;

public interface MealGroupsRepository
        extends CrudRepository<MealGroup, Long> {
}

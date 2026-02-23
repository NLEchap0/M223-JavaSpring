package ch.samt.esercizio2.model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @NotNull(message = "ID is mandatory")
    @Positive(message = "ID must be a positive number")
    private Integer id;

    @NotBlank(message = "Name is mandatory")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    private String name;

    @NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    private Double price;

    @FutureOrPresent(message = "Expiration date cannot be in the past")
    private LocalDate expirationDate;

    // Added in Step 10
    @NotBlank(message = "Description is mandatory")
    private String description;
}
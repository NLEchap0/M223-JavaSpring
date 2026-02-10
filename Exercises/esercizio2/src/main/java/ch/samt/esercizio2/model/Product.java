package ch.samt.esercizio2.model;

import jakarta.validation.constraints.*;
import lombok.*;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Product {
    @NotEmpty(message = "This field is mandatory")
    @Positive(message = "The number must be greater or equals than 0")
    private Integer id;

    @NotBlank(message = "This field is mandatory")
    @Size(min = 2, max = 100, message = "It must contain 2 characters and a maximum of 100")
    private String name;

    @NotEmpty(message = "This field is mandatory")
    @Positive(message = "The number must be greater or equals than 0")
    private Double price;

    @NotNull(message = "This field is mandatory")
    @FutureOrPresent(message = "Date cannot be in the past")
    private LocalDate releaseDate;
}

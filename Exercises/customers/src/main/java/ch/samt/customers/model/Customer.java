package ch.samt.customers.model;

import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    private Long id;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lunghezza tra 2 e 10 caratteri.")
    private String name;

    @NotBlank
    @Size(min = 2, max = 10, message = "Lunghezza tra 2 e 10 caratteri.")
    private String surname;

    @NotNull
    @Min(18)
    @Max(99)
    private Integer age;
}

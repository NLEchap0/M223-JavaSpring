package ch.samt.clockcollection.models;

import lombok.*;
import jakarta.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Clock {
    @NotNull
    private Long id;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @NotNull
    @Min(0)
    private Integer price;
}

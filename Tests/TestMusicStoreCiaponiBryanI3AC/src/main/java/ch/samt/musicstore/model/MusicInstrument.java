package ch.samt.musicstore.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MusicInstrument {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "mi_seq")
    @SequenceGenerator(name = "mi_seq", sequenceName = "mi_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String type;

    @NotBlank
    private String brand;

    @NotBlank
    private String model;

    @Min(1)
    @NotNull
    private double price;

    @Min(0)
    @NotNull
    private int stock;
}

package ch.samt.gardenwarehouse.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(name = "item_seq", sequenceName = "item_seq", allocationSize = 1)
    @Column(unique = true, nullable = false)
    private Long id;

    @NotEmpty
    @Pattern(regexp = "^(0[a-z]{3})-[0-9]{2}$", message = "Formato richiesto: abc-12")
    @Size(min = 6, max = 6)
    private String code;

    @NotEmpty
    private String type;

    @NotEmpty
    private String name;

    @NotNull
    @Min(0)
    private double price;

    @NotNull
    @Min(0)
    private int itemCount;
}

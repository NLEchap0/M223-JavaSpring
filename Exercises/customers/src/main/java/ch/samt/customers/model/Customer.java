package ch.samt.customers.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
@Entity // Assicurati che ci sia @Entity per JPA
@Table(name = "Customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
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

    @NotBlank
    @Size(min = 3, max = 20)
    private String city;

    //@CreditCardNumber
    @NotBlank
    @Column(name = "cc_number")
    private String ccNumber;

    @NotBlank
    @Column(name = "cc_expiration")
    @Pattern(regexp = "^(0[1-9]|1[0-2])/[0-9]{2}$", message = "Formato richiesto: MM/YY")
    private String ccExpiration;

    @NotBlank
    @Column(name = "cc_cvv")
    @Digits(integer = 3, fraction = 0, message = "Deve essere un CVV di 3 cifre")
    private String ccCVV;
}

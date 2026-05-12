package ch.samt.customers.model;

import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Entity // Assicurati che ci sia @Entity per JPA
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "reservation_seq", allocationSize = 1)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    @Valid
    private Customer customer;

    @NotBlank
    @Size(min = 1, max = 3, message = "Numero camera deve essere tra 1 e 999")
    String room;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkin tra 8 e 16 caratteri")
    String checkIn;

    @NotBlank
    @Size(min = 8, max = 16, message = "Ora del checkout tra 8 e 16 caratteri")
    String checkOut;
}

package ch.samt.customers.service;

import ch.samt.customers.data.ReservationRepository;
import ch.samt.customers.model.Reservation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationService {

    private ReservationRepository reservationRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    public Iterable<Reservation> findAll() {
        return reservationRepository.findAll();
    }
}

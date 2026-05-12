package ch.samt.customers.controller;

import ch.samt.customers.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ReservationController {
    private ReservationService reservationService;

    @Autowired
    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/loadCustomers/reservations")
    public String loadReservations(Model model) {
        model.addAttribute("reservations", reservationService.findAll());
        return "reservationList";
    }
}

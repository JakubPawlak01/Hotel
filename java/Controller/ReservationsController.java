package Controller;

import Entity.Reservations;
import Repository.ReservationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {

    private final ReservationsRepository reservationsRepository;

    @Autowired
    public ReservationsController(ReservationsRepository reservationsRepository) {
        this.reservationsRepository = reservationsRepository;
    }

    @GetMapping
    public List<Reservations> getAllReservations() {
        return reservationsRepository.findAll();
    }

    @GetMapping("/{reservation_id}")
    public Reservations getReservationsById(@PathVariable Long reservation_id) {
        return reservationsRepository.findById(reservation_id).orElse(null);
    }

    @PostMapping
    public Reservations createReservations(@RequestBody Reservations reservations) {
        return reservationsRepository.save(reservations);
    }

    @PutMapping("/{reservation_id}")
    public Reservations updateReservations(@PathVariable Long reservation_id, @RequestBody Reservations updatedReservations) {
        Reservations existingReservations = reservationsRepository.findById(reservation_id).orElse(null);
        if (existingReservations != null) {
            existingReservations.setClient_id(updatedReservations.getClient_id());
            existingReservations.setRoom_id(updatedReservations.getRoom_id());
            existingReservations.setPayment_method_id(updatedReservations.getPayment_method_id());
            existingReservations.setCheck_in_date(updatedReservations.getCheck_in_date());
            existingReservations.setCheck_out_date(updatedReservations.getCheck_out_date());
            existingReservations.setStatus(updatedReservations.getStatus());
            existingReservations.setSpecial_request(updatedReservations.getSpecial_request());
            existingReservations.setCreated_at(updatedReservations.getCreated_at());
            existingReservations.setUpdated_at(updatedReservations.getUpdated_at());
            return reservationsRepository.save(existingReservations);
        }
        return null;
    }

    @DeleteMapping("/{reservation_id}")
    public void deleteReservations(@PathVariable Long reservation_id) {
        reservationsRepository.deleteById(reservation_id);
    }
}

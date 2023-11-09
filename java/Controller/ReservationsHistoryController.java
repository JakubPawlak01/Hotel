package Controller;

import Entity.ReservationsHistory;
import Repository.ReservationsHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservations-history")
public class ReservationsHistoryController {

    private final ReservationsHistoryRepository reservationsHistoryRepository;

    @Autowired
    public ReservationsHistoryController(ReservationsHistoryRepository reservationsHistoryRepository) {
        this.reservationsHistoryRepository = reservationsHistoryRepository;
    }

    @GetMapping
    public List<ReservationsHistory> getAllReservationsHistory() {
        return reservationsHistoryRepository.findAll();
    }

    @GetMapping("/{history_id}")
    public ReservationsHistory getReservationsHistoryById(@PathVariable Long history_id) {
        return reservationsHistoryRepository.findById(history_id).orElse(null);
    }

    @PostMapping
    public ReservationsHistory createReservationsHistory(@RequestBody ReservationsHistory reservationsHistory) {
        return reservationsHistoryRepository.save(reservationsHistory);
    }

    @PutMapping("/{history_id}")
    public ReservationsHistory updateReservationsHistory(@PathVariable Long history_id, @RequestBody ReservationsHistory updatedReservationsHistory) {
        ReservationsHistory existingReservationsHistory = reservationsHistoryRepository.findById(history_id).orElse(null);
        if (existingReservationsHistory != null) {
            existingReservationsHistory.setClient_id(updatedReservationsHistory.getClient_id());
            existingReservationsHistory.setReservation_id(updatedReservationsHistory.getReservation_id());
            existingReservationsHistory.setOpinion(updatedReservationsHistory.getOpinion());
            return reservationsHistoryRepository.save(existingReservationsHistory);
        }
        return null;
    }

    @DeleteMapping("/{history_id}")
    public void deleteReservationsHistory(@PathVariable Long history_id) {
        reservationsHistoryRepository.deleteById(history_id);
    }
}

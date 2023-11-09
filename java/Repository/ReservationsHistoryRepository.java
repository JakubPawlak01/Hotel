package Repository;

import Entity.ReservationsHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsHistoryRepository extends JpaRepository<ReservationsHistory, Long> {
    // Dodatkowe metody związane z operacjami bazodanowymi można tutaj zadeklarować
}

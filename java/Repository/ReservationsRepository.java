package Repository;

import Entity.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations, Long> {
    // Dodatkowe metody związane z operacjami bazodanowymi można tutaj zadeklarować
}

package Repository;

import Entity.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Rooms, Long> {
    // Dodatkowe metody związane z operacjami bazodanowymi można tutaj zadeklarować
}

package Repository;

import Entity.Hotels;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelsRepository extends JpaRepository<Hotels, Long> {
    // Dodatkowe metody związane z operacjami bazodanowymi można tutaj zadeklarować
}

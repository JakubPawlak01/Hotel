package Repository;

import Entity.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentMethodRepository extends JpaRepository<PaymentMethod, Long> {
    // Dodatkowe metody związane z operacjami bazodanowymi można tutaj zadeklarować
}

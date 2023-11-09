package Controller;

import Entity.PaymentMethod;
import Repository.PaymentMethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {

    private final PaymentMethodRepository paymentMethodRepository;

    @Autowired
    public PaymentMethodController(PaymentMethodRepository paymentMethodRepository) {
        this.paymentMethodRepository = paymentMethodRepository;
    }

    @GetMapping
    public List<PaymentMethod> getAllPaymentMethods() {
        return paymentMethodRepository.findAll();
    }

    @GetMapping("/{payment_method_id}")
    public PaymentMethod getPaymentMethodById(@PathVariable Long payment_method_id) {
        return paymentMethodRepository.findById(payment_method_id).orElse(null);
    }

    @PostMapping
    public PaymentMethod createPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        return paymentMethodRepository.save(paymentMethod);
    }

    @PutMapping("/{payment_method_id}")
    public PaymentMethod updatePaymentMethod(@PathVariable Long payment_method_id, @RequestBody PaymentMethod updatedPaymentMethod) {
        PaymentMethod existingPaymentMethod = paymentMethodRepository.findById(payment_method_id).orElse(null);
        if (existingPaymentMethod != null) {
            existingPaymentMethod.setMethod_name(updatedPaymentMethod.getMethod_name());
            existingPaymentMethod.setDescription(updatedPaymentMethod.getDescription());
            return paymentMethodRepository.save(existingPaymentMethod);
        }
        return null;
    }

    @DeleteMapping("/{payment_method_id}")
    public void deletePaymentMethod(@PathVariable Long payment_method_id) {
        paymentMethodRepository.deleteById(payment_method_id);
    }
}

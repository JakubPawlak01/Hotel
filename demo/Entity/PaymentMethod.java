package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "payment_method")
public class PaymentMethod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "payment_method_id", nullable = false)
    private Long payment_method_id;

    @Column(name = "metohd_name", nullable = false)
    private String method_name;

    @Column(name = "description", nullable = true)
    private String description;

    public PaymentMethod(Long payment_method_id, String method_name, String description) {
        this.payment_method_id = payment_method_id;
        this.method_name = method_name;
        this.description = description;
    }

    public Long getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(Long payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public String getMethod_name() {
        return method_name;
    }

    public void setMethod_name(String method_name) {
        this.method_name = method_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PaymentMethod that = (PaymentMethod) o;
        return Objects.equals(payment_method_id, that.payment_method_id) && Objects.equals(method_name, that.method_name) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(payment_method_id, method_name, description);
    }
}

package com.example.demo.Entity;

import jakarta.persistence.*;

import java.time.LocalTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "reservations")

public class Reservations {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id", nullable = false)
    private Long reservation_id;

    @Column(name = "client_id", nullable = false)
    private Long client_id;

    @Column(name = "room_id", nullable = false)
    private Long room_id;

    @Column(name = "payment_method_id", nullable = false)
    private Long payment_method_id;

    @Column(name = "check_in_date", nullable = false)
    private Date check_in_date;

    @Column(name = "check_out_date", nullable = false)
    private Date check_out_date;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "special_request", nullable = true)
    private String special_request;

    @Column(name = "created_at", nullable = false)
    private LocalTime created_at;

    @Column(name = "updated_at", nullable = false)
    private LocalTime updated_at;

    public Reservations(Long reservation_id, Long client_id, Long room_id, Long payment_method_id, Date check_in_date, Date check_out_date, String status, String special_request, LocalTime created_at, LocalTime updated_at) {
        this.reservation_id = reservation_id;
        this.client_id = client_id;
        this.room_id = room_id;
        this.payment_method_id = payment_method_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.status = status;
        this.special_request = special_request;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getPayment_method_id() {
        return payment_method_id;
    }

    public void setPayment_method_id(Long payment_method_id) {
        this.payment_method_id = payment_method_id;
    }

    public Date getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(Date check_in_date) {
        this.check_in_date = check_in_date;
    }

    public Date getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(Date check_out_date) {
        this.check_out_date = check_out_date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSpecial_request() {
        return special_request;
    }

    public void setSpecial_request(String special_request) {
        this.special_request = special_request;
    }

    public LocalTime getCreated_at() {
        return created_at;
    }

    public void setCreated_at(LocalTime created_at) {
        this.created_at = created_at;
    }

    public LocalTime getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(LocalTime updated_at) {
        this.updated_at = updated_at;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Reservations that = (Reservations) o;
        return Objects.equals(reservation_id, that.reservation_id) && Objects.equals(client_id, that.client_id) && Objects.equals(room_id, that.room_id) && Objects.equals(payment_method_id, that.payment_method_id) && Objects.equals(check_in_date, that.check_in_date) && Objects.equals(check_out_date, that.check_out_date) && Objects.equals(status, that.status) && Objects.equals(special_request, that.special_request) && Objects.equals(created_at, that.created_at) && Objects.equals(updated_at, that.updated_at);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation_id, client_id, room_id, payment_method_id, check_in_date, check_out_date, status, special_request, created_at, updated_at);
    }
}

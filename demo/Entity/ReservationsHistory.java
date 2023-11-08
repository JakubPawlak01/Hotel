package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "reservations_history")
public class ReservationsHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "history_id", nullable = false)
    private Long history_id;

    @Column(name = "client_id", nullable = false)
    private Long client_id;

    @Column(name = "reservation_id", nullable = false)
    private Long reservation_id;

    @Column(name = "opinion", nullable = true)
    private String opinion;

    public ReservationsHistory(Long history_id, Long client_id, Long reservation_id, String opinion) {
        this.history_id = history_id;
        this.client_id = client_id;
        this.reservation_id = reservation_id;
        this.opinion = opinion;
    }

    public ReservationsHistory(Long history_id, Long client_id, Long reservation_id) {
        this.history_id = history_id;
        this.client_id = client_id;
        this.reservation_id = reservation_id;
    }

    public Long getHistory_id() {
        return history_id;
    }

    public void setHistory_id(Long history_id) {
        this.history_id = history_id;
    }

    public Long getClient_id() {
        return client_id;
    }

    public void setClient_id(Long client_id) {
        this.client_id = client_id;
    }

    public Long getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(Long reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getOpinion() {
        return opinion;
    }

    public void setOpinion(String opinion) {
        this.opinion = opinion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReservationsHistory that = (ReservationsHistory) o;
        return Objects.equals(history_id, that.history_id) && Objects.equals(client_id, that.client_id) && Objects.equals(reservation_id, that.reservation_id) && Objects.equals(opinion, that.opinion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history_id, client_id, reservation_id, opinion);
    }

}

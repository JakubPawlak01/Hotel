package model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class Rezerwacja implements Serializable {
    private int id;
    private int idPokoju;
    private int idKlienta;
    private LocalDate checkInDate;
    private LocalDate checkOutDate;
    private String uwagi;

    public Rezerwacja(int idPokoju, int idKlienta, LocalDate checkInDate, LocalDate checkOutDate, String uwagi) {
        this.idPokoju = idPokoju;
        this.idKlienta = idKlienta;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.uwagi = uwagi;
    }

    public Rezerwacja(int id, int idPokoju, int idKlienta, LocalDate checkInDate, LocalDate checkOutDate, String uwagi) {
        this.id = id;
        this.idPokoju = idPokoju;
        this.idKlienta = idKlienta;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.uwagi = uwagi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdPokoju() {
        return idPokoju;
    }

    public void setIdPokoju(int idPokoju) {
        this.idPokoju = idPokoju;
    }

    public int getIdKlienta() {
        return idKlienta;
    }

    public void setIdKlienta(int idKlienta) {
        this.idKlienta = idKlienta;
    }

    public LocalDate getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(LocalDate checkInDate) {
        this.checkInDate = checkInDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(LocalDate checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }

    @Override
    public String toString() {
        return "Rezerwacja: " + "id=" + id + ", idPokoju=" + idPokoju + ", idKlienta=" + idKlienta + ", checkInDate=" + checkInDate + ", checkOutDate=" + checkOutDate + ", uwagi='" + uwagi + '\'';
    }
}

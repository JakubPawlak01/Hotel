package model;

import java.io.Serializable;

public class Hotel implements Serializable {
    private int id;
    private String nazwa;
    private String adres;
    private int iloscPieter;
    private String opis;

    public Hotel(int id, String nazwa, String adres, int iloscPieter, String opis) {
        this.id = id;
        this.nazwa = nazwa;
        this.adres = adres;
        this.iloscPieter = iloscPieter;
        this.opis = opis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public int getIloscPieter() {
        return iloscPieter;
    }

    public void setIloscPieter(int iloscPieter) {
        this.iloscPieter = iloscPieter;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    @Override
    public String toString() {
        return "Hotel: "  + nazwa + ", " + adres + ", iloscPieter: " + iloscPieter + ", opis: " + opis;
    }
}

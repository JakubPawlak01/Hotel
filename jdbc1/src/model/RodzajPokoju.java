package model;

import java.io.Serializable;

public class RodzajPokoju implements Serializable {
    private String nazwa;
    private int ileLozek;
    private int ileOsob;
    private double cena;

    public RodzajPokoju() {
    }

    public RodzajPokoju(String nazwa, int ileLozek, int ileOsob, double cena) {
        this.nazwa = nazwa;
        this.ileLozek = ileLozek;
        this.ileOsob = ileOsob;
        this.cena = cena;
    }

    @Override
    public String toString() {
        return "RodzajPokoju: " + nazwa + ", " + ", ilość łóżek: " + ileLozek + ", ilość osob: " + ileOsob + ", cena za noc: " + cena;
    }

    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public int getIleLozek() {
        return ileLozek;
    }

    public void setIleLozek(int ileLozek) {
        this.ileLozek = ileLozek;
    }

    public int getIleOsob() {
        return ileOsob;
    }

    public void setIleOsob(int ileOsob) {
        this.ileOsob = ileOsob;
    }

    public double getCena() {
        return cena;
    }

    public void setCena(double cena) {
        this.cena = cena;
    }
}

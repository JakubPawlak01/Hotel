package model;


public class Klient {
    private int id;
    private String imie;
    private String nazwisko;
    private String email;
    private String haslo;
    private String adres;
    private String telefon;
    private boolean stalyKlient;

    public Klient(int id, String imie, String nazwisko, String email, String haslo, String adres, String telefon, boolean stalyKlient) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.adres = adres;
        this.telefon = telefon;
        this.stalyKlient = stalyKlient;
    }
    public Klient(int id, String imie, String nazwisko, String email, String haslo, String adres, String telefon) {
        this.id = id;
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.adres = adres;
        this.telefon = telefon;
        this.stalyKlient = false;
    }

    public Klient(String imie, String nazwisko, String email, String haslo, String adres, String telefon) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.email = email;
        this.haslo = haslo;
        this.adres = adres;
        this.telefon = telefon;
        stalyKlient = false;
    }
    public Klient() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public boolean isStalyKlient() {
        return stalyKlient;
    }

    public void setStalyKlient(boolean stalyKlient) {
        this.stalyKlient = stalyKlient;
    }
}


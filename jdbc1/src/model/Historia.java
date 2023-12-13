package model;

public class Historia {
    private int id;
    private int idRezerwacji;
    private int ocena;
    private String uwagi;

    public Historia(int id, int idRezerwacji, int ocena, String uwagi) {
        this.id = id;
        this.idRezerwacji = idRezerwacji;
        this.ocena = ocena;
        this.uwagi = uwagi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdRezerwacji() {
        return idRezerwacji;
    }

    public void setIdRezerwacji(int idRezerwacji) {
        this.idRezerwacji = idRezerwacji;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }

    public String getUwagi() {
        return uwagi;
    }

    public void setUwagi(String uwagi) {
        this.uwagi = uwagi;
    }
}

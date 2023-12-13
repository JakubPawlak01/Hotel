package model;

public class Pokoj {
    private int idPokoju;
    private String nazwaPokoju;
    private int idHotelu;
    private int pietro;

    public Pokoj() {
    }

    public Pokoj(String nazwaPokoju, int idHotelu, int pietro) {
        this.nazwaPokoju = nazwaPokoju;
        this.idHotelu = idHotelu;
        this.pietro = pietro;
    }

    public Pokoj(int idPokoju, String nazwaPokoju, int idHotelu, int pietro) {
        this.idPokoju = idPokoju;
        this.nazwaPokoju = nazwaPokoju;
        this.idHotelu = idHotelu;
        this.pietro = pietro;
    }

    public int getIdPokoju() {
        return idPokoju;
    }

    public void setIdPokoju(int idPokoju) {
        this.idPokoju = idPokoju;
    }

    public String getNazwaPokoju() {
        return nazwaPokoju;
    }

    public void setNazwaPokoju(String nazwaPokoju) {
        this.nazwaPokoju = nazwaPokoju;
    }

    public int getIdHotelu() {
        return idHotelu;
    }

    public void setIdHotelu(int idHotelu) {
        this.idHotelu = idHotelu;
    }

    public int getPietro() {
        return pietro;
    }

    public void setPietro(int pietro) {
        this.pietro = pietro;
    }

    @Override
    public String toString() {
        return "Pokoj{" + "idPokoju=" + idPokoju + ", nazwaPokoju='" + nazwaPokoju + '\'' + ", idHotelu=" + idHotelu + ", pietro=" + pietro + '}';
    }
}

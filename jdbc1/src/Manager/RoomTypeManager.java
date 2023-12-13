package Manager;

import model.Hotel;
import model.Pokoj;
import model.RodzajPokoju;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomTypeManager {

    public boolean dodajRodzajPokoju(RodzajPokoju rodzajPokoju) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO rodzaj_pokoju (nazwa, ile_lozek, ile_osob, cena) VALUES (?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, rodzajPokoju.getNazwa());
                statement.setInt(2, rodzajPokoju.getIleLozek());
                statement.setInt(3, rodzajPokoju.getIleOsob());
                statement.setDouble(4, rodzajPokoju.getCena());

                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RodzajPokoju> pobierzDostepneRodzajePokoi() {
        List<RodzajPokoju> rodzajePokoi = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM rodzaj_pokoju";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        String nazwa = resultSet.getString("nazwa");
                        int ileLozek = resultSet.getInt("ile_lozek");
                        int ileOsob = resultSet.getInt("ile_osob");
                        double cena = resultSet.getDouble("cena");
                        RodzajPokoju rodzajPokoju = new RodzajPokoju(nazwa, ileLozek, ileOsob, cena);
                        rodzajePokoi.add(rodzajPokoju);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rodzajePokoi;
    }

    public RodzajPokoju wybierzPokoj(String wybranyPokoj) {
        List<RodzajPokoju> rodzajePokoju = pobierzDostepneRodzajePokoi();

        for (RodzajPokoju pokoj : rodzajePokoju) {
            if (pokoj.getNazwa().equals(wybranyPokoj)) {
                return pokoj;
            }
        }

        return null;
    }
}

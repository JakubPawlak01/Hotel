package Manager;

import model.Pokoj;
import model.RodzajPokoju;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RoomManager {

    public List<Pokoj> pobierzPokojeWedlugTypu(String rodzajPokoju, int idHotelu) {
        List<Pokoj> pokoje = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM pokoj WHERE Nazwa_pokoju = ? AND id_hotelu = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, rodzajPokoju);
                statement.setInt(2, idHotelu);

                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int idPokoju = resultSet.getInt("id_pokoju");
                        String nazwaPokoju = resultSet.getString("nazwa_pokoju");
                        int pietro = resultSet.getInt("pietro");
                        Pokoj pokoj = new Pokoj(idPokoju, nazwaPokoju, idHotelu, pietro);
                        pokoje.add(pokoj);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pokoje;
    }

    public boolean dodajPokoj(Pokoj pokoj) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO pokoj (nazwa_pokoju, id_hotelu, pietro) VALUES (?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, pokoj.getNazwaPokoju());
                statement.setInt(2, pokoj.getIdHotelu());
                statement.setInt(3, pokoj.getPietro());

                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean usunPokoj(int idPokoju) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "DELETE FROM pokoj WHERE id_pokoju = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setInt(1, idPokoju);

                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
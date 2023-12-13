package Manager;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class UserManager {

    public Klient zarejestrujKlienta(Klient klient) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "INSERT INTO klient (imie, nazwisko, email, haslo, adres, telefon) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                statement.setString(1, klient.getImie());
                statement.setString(2, klient.getNazwisko());
                statement.setString(3, klient.getEmail());
                statement.setString(4, klient.getHaslo());
                statement.setString(5, klient.getAdres());
                statement.setString(6, klient.getTelefon());

                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            klient.setId(generatedKeys.getInt(1));
                            return klient;
                        } else {
                            throw new SQLException("Błąd podczas pobierania ID klienta po zarejestrowaniu.");
                        }
                    }
                } else {
                    return null; // Rejestracja nie powiodła się
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null; // Błąd podczas rejestracji
        }
    }

    public Klient zalogujKlienta(String email, String haslo) {
        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM klient WHERE email = ? AND haslo = ?";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                statement.setString(1, email);
                statement.setString(2, haslo);

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        int idKlienta = resultSet.getInt("id_klienta");
                        String imie = resultSet.getString("imie");
                        String nazwisko = resultSet.getString("nazwisko");
                        String adres = resultSet.getString("adres");
                        String telefon = resultSet.getString("telefon");
                        Boolean stalyKlient = resultSet.getBoolean("Staly_klient") ;

                        // Tworzymy obiekt klienta na podstawie danych z bazy
                        Klient klient = new Klient(idKlienta, imie, nazwisko, email, haslo, adres, telefon, stalyKlient);
                        return klient;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // Zwracamy null, jeśli klient nie został znaleziony
    }
}

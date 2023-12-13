package Manager;

import model.Pokoj;
import model.Rezerwacja;

import java.sql.*;
import java.time.LocalDate;
import java.util.List;

public class ReservationManager {

    private final RoomManager roomManager;

    public ReservationManager(RoomManager roomManager) {
        this.roomManager = roomManager;
    }

    public Rezerwacja stworzRezerwacje(int idKlienta, Pokoj pokoj, LocalDate checkInDate, LocalDate checkOutDate, String uwagi) {
        try {
            if (czyRezerwacjaMozliwa(pokoj.getIdHotelu(), pokoj.getNazwaPokoju(), checkInDate, checkOutDate) != null) {
                String query = "INSERT INTO rezerwacja (id_pokoju, id_klienta, check_in_date, check_out_date, uwagi) VALUES (?, ?, ?, ?, ?)";
                try (Connection connection = DatabaseManager.getConnection();
                     PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
                    statement.setInt(1, pokoj.getIdPokoju());
                    statement.setInt(2, idKlienta);
                    statement.setDate(3, java.sql.Date.valueOf(checkInDate));
                    statement.setDate(4, java.sql.Date.valueOf(checkOutDate));
                    statement.setString(5, uwagi);

                    int rowsAffected = statement.executeUpdate();

                    if (rowsAffected > 0) {
                        // Pobierz wygenerowany klucz (ID) rezerwacji
                        ResultSet generatedKeys = statement.getGeneratedKeys();
                        if (generatedKeys.next()) {
                            int idRezerwacji = generatedKeys.getInt(1);

                            // Zwróć obiekt Rezerwacja z ustawionym ID
                            return new Rezerwacja(idRezerwacji, pokoj.getIdPokoju(), idKlienta, checkInDate, checkOutDate, uwagi);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Rezerwacja niemożliwa lub wystąpił błąd
    }

    public Pokoj czyRezerwacjaMozliwa(int idHotelu, String rodzajPokoju, LocalDate checkInDate, LocalDate checkOutDate) {
        try {
            List<Pokoj> dostepnePokoje = roomManager.pobierzPokojeWedlugTypu(rodzajPokoju, idHotelu);

            for (Pokoj pokoj : dostepnePokoje) {
                if (czyPokojDostepny(pokoj.getIdPokoju(), checkInDate, checkOutDate) && !czyIstniejeRezerwacja(pokoj.getIdPokoju(), checkInDate, checkOutDate)) {
                    return pokoj; // Rezerwacja możliwa
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Brak dostępnego pokoju lub istniejąca rezerwacja
    }

    private boolean czyPokojDostepny(int idPokoju, LocalDate checkInDate, LocalDate checkOutDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM rezerwacja WHERE id_pokoju = ? AND ((Check_in_date BETWEEN ? AND ?) OR (Check_out_date BETWEEN ? AND ?))";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPokoju);
            statement.setDate(2, java.sql.Date.valueOf(checkInDate));
            statement.setDate(3, java.sql.Date.valueOf(checkOutDate));
            statement.setDate(4, java.sql.Date.valueOf(checkInDate));
            statement.setDate(5, java.sql.Date.valueOf(checkOutDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) == 0;
            }
        }
    }

    private boolean czyIstniejeRezerwacja(int idPokoju, LocalDate checkInDate, LocalDate checkOutDate) throws SQLException {
        String query = "SELECT COUNT(*) FROM rezerwacja WHERE id_pokoju = ? AND ((Check_in_date BETWEEN ? AND ?) OR (Check_out_date BETWEEN ? AND ?))";
        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setInt(1, idPokoju);
            statement.setDate(2, java.sql.Date.valueOf(checkInDate));
            statement.setDate(3, java.sql.Date.valueOf(checkOutDate));
            statement.setDate(4, java.sql.Date.valueOf(checkInDate));
            statement.setDate(5, java.sql.Date.valueOf(checkOutDate));

            try (ResultSet resultSet = statement.executeQuery()) {
                return resultSet.next() && resultSet.getInt(1) > 0;
            }
        }
    }
}

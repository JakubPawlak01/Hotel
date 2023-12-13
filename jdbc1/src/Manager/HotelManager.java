package Manager;

import model.Hotel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HotelManager {

    public List<Hotel> pobierzDostepneHotele() {
        List<Hotel> hotele = new ArrayList<>();

        try (Connection connection = DatabaseManager.getConnection()) {
            String query = "SELECT * FROM hotel";
            try (PreparedStatement statement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = statement.executeQuery()) {
                    while (resultSet.next()) {
                        int id = resultSet.getInt("id_hotelu");
                        String nazwa = resultSet.getString("nazwa");
                        String adres = resultSet.getString("adres");
                        int iloscPieter = resultSet.getInt("ilosc_pieter");
                        String opis = resultSet.getString("opis");
                        Hotel hotel = new Hotel(id, nazwa, adres, iloscPieter, opis);
                        hotele.add(hotel);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return hotele;
    }

    public Hotel wybierzHotel(String wybranyHotel) {
        List<Hotel> hotele = pobierzDostepneHotele();

        for (Hotel hotel : hotele) {
            if (hotel.getNazwa().equals(wybranyHotel)) {
                return hotel;
            }
        }

        return null; // Jeśli hotel o podanym ID nie został znaleziony
    }
}

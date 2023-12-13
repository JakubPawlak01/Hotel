import Manager.*;
import model.*;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final UserManager signManager;
    private final HotelManager hotelManager;
    private final RoomTypeManager roomTypeManager;
    private final RoomManager roomManager;
    private final ReservationManager reservationManager;
    private String wybranyRodzaj;
    private int idZalogowanegoKlienta;
    private int idWybranegoHotelu;
    private LocalDate dataPrzyjazdu;
    private LocalDate dataWyjazdu;
    private Pokoj wybranyPokoj;

    public ClientHandler(Socket clientSocket, UserManager signManager, HotelManager hotelManager, RoomTypeManager roomTypeManager, RoomManager roomManager, ReservationManager reservationManager) {
        this.clientSocket = clientSocket;
        this.signManager = signManager;
        this.hotelManager = hotelManager;
        this.roomTypeManager = roomTypeManager;
        this.roomManager = roomManager;
        this.reservationManager = reservationManager;
    }

    @Override
    public void run() {
        try (ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
             ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())) {

            while (true) {
                // Odczytaj opcję klienta jako ciąg znaków
                String choice = (String) input.readObject();

                if(choice.equals("EXIT")){
                    String responseMessage = "DO widzenia!";
                    output.writeObject(responseMessage);
                    break;
                }

                // Przykładowa obsługa wyboru klienta
                else if (choice.equals("LOGIN")) { // Logowanie
                    String responseMessage;
                    String email = (String) input.readObject();
                    String password = (String) input.readObject();

                    Klient zalogowanyKlient = signManager.zalogujKlienta(email, password);

                    if (zalogowanyKlient != null) {
                        responseMessage = "SUCCESS";
                        idZalogowanegoKlienta = zalogowanyKlient.getId();
                    } else {
                        responseMessage = "FAILURE";
                    }

                    // Odpowiedz klientowi
                    output.writeObject(responseMessage);

                } else if (choice.equals("REGISTER")) { // Rejestracja
                    String responseMessage;
                    String imie = (String) input.readObject();
                    String nazwisko = (String) input.readObject();
                    String email = (String) input.readObject();
                    String haslo = (String) input.readObject();
                    String adres = (String) input.readObject();
                    String telefon = (String) input.readObject();
                    Klient klient = new Klient(imie, nazwisko, email, haslo, adres, telefon);

                    Klient zalogowanyKlient = signManager.zarejestrujKlienta(klient);

                    if (zalogowanyKlient != null) {
                        responseMessage = "SUCCESS";
                        idZalogowanegoKlienta = zalogowanyKlient.getId();
                    } else {
                        responseMessage = "FAILURE";
                    }

                    // Odpowiedz klientowi
                    output.writeObject(responseMessage);

                } else if (choice.equals("SELECT_HOTEL")) {
                    String responseMessage;
                    List<Hotel> hotele = hotelManager.pobierzDostepneHotele();
                    output.writeObject(hotele);

                    String wybor = (String) input.readObject();
                    Hotel udanyWybor = hotelManager.wybierzHotel(wybor);
                    if (udanyWybor != null) {
                        responseMessage = "SUCCESS";
                        idWybranegoHotelu = udanyWybor.getId();
                    } else {
                        responseMessage = "FAILURE";
                    }
                    output.writeObject(responseMessage);
                } else if (choice.equals("SELECT_ROOM_TYPE")) {
                    String responseMessage;
                    List<RodzajPokoju> rodzaj = roomTypeManager.pobierzDostepneRodzajePokoi();
                    output.writeObject(rodzaj);

                    String wyborPokoju = (String) input.readObject();
                    RodzajPokoju udanyWybor = roomTypeManager.wybierzPokoj(wyborPokoju);
                    if (udanyWybor != null) {
                        responseMessage = "SUCCESS";
                        wybranyRodzaj = udanyWybor.getNazwa();
                    } else {
                        responseMessage = "FAILURE";
                    }
                    output.writeObject(responseMessage);
                }else if(choice.equals("CHECK_ROOM")){
                    String responseMessage;
                    dataPrzyjazdu = (LocalDate) input.readObject();
                    dataWyjazdu = (LocalDate) input.readObject();

                    Pokoj wolnyPokoj = reservationManager.czyRezerwacjaMozliwa(idWybranegoHotelu, wybranyRodzaj, dataPrzyjazdu, dataWyjazdu);
                    if(wolnyPokoj != null){
                        responseMessage = "Mamy wolny pokój!";
                        wybranyPokoj = wolnyPokoj;
                    }else {
                        responseMessage = "Nie mamy wolnego pokoju";
                    }
                    output.writeObject(responseMessage);
                }else if(choice.equals("MAKE_RESERVATION")){
                    String responseMessage;
                    String uwagi = (String) input.readObject();
                    Rezerwacja utworzonaRezerwacja = reservationManager.stworzRezerwacje(idZalogowanegoKlienta, wybranyPokoj, dataPrzyjazdu, dataWyjazdu, uwagi);
                    if(utworzonaRezerwacja != null){
                        responseMessage = "Rezerwacja utworzona! \n" + utworzonaRezerwacja;
                    }
                    else {
                        responseMessage = "Nie udało się utworzyć rezerwacji!";
                    }
                    output.writeObject(responseMessage);
                } else {
                    // Nieznana opcja - obsłuż błąd
                    String responseMessage = "Nieznana opcja.";
                    output.writeObject(responseMessage);
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
                System.out.println("Klient rozłączony.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}

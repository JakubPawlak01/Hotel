package Client;

import model.Hotel;
import model.RodzajPokoju;

import java.io.*;
import java.net.Socket;
import java.time.LocalDate;
import java.util.List;

public class Client {

    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 5555;
    private static boolean loggedIn = false;
    private static boolean hotelSelected = false;
    private static boolean roomTypeSelected = false;
    private static boolean roomAvailabilityChecked = false;

    public static void main(String[] args) {
        try {
            startClient();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void startClient() throws IOException, ClassNotFoundException {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
             BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                displayMenu();
                int userChoice = getUserChoice(consoleInput);
                processUserChoice(userChoice, output, input, consoleInput);
            }
        }
    }

    private static void displayMenu() {
        System.out.println("Dostępne opcje:");

        if (!loggedIn) {
            System.out.println("1. Zaloguj się");
            System.out.println("2. Zarejestruj się");
        }

        if (!hotelSelected && loggedIn) {
            System.out.println("3. Wybierz hotel");
        }

        if (!roomTypeSelected && hotelSelected) {
            System.out.println("4. Wybierz rodzaj pokoju");
        }

        if (!roomAvailabilityChecked && roomTypeSelected) {
            System.out.println("5. Sprawdź dostępność pokoju");
        }

        if (roomAvailabilityChecked) {
            System.out.println("6. Złóż rezerwację");
        }

        System.out.println("0. Wyjdź");
    }

    private static int getUserChoice(BufferedReader consoleInput) throws IOException {
        System.out.print("Wybierz opcję: ");
        return Integer.parseInt(consoleInput.readLine());
    }

    private static void processUserChoice(int userChoice, ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        switch (userChoice) {
            case 0:
                handleExit(output, input);
                break;
            case 1:
                handleLogin(output, input, consoleInput);
                break;
            case 2:
                handleRegistration(output, input, consoleInput);
                break;
            case 3:
                handleSelectHotel(output, input, consoleInput);
                break;
            case 4:
                handleSelectRoomType(output, input, consoleInput);
                break;
            case 5:
                handleCheckRoom(output, input, consoleInput);
                break;
            case 6:
                handleMakeReservation(output, input, consoleInput);
                break;
            default:
                System.out.println("Nieprawidłowy wybór. Spróbuj ponownie.");
        }
    }

    private static void handleExit(ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        String choice = "EXIT";
        output.writeObject(choice);
        String exitMessage = (String) input.readObject();
        System.out.println(exitMessage);
        System.exit(0);
    }

    private static void handleLogin(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        String choice = "LOGIN";
        output.writeObject(choice);
        System.out.print("Podaj email: ");
        String email = consoleInput.readLine();
        System.out.print("Podaj hasło: ");
        String password = consoleInput.readLine();
        output.writeObject(email);
        output.writeObject(password);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);

        if (responseMessage.equals("SUCCESS")) {
            loggedIn = true;
        }
    }

    private static void handleRegistration(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        String choice = "REGISTER";
        output.writeObject(choice);
        System.out.print("Podaj imię: ");
        String imie = consoleInput.readLine();
        System.out.print("Podaj nazwisko: ");
        String nazwisko = consoleInput.readLine();
        System.out.print("Podaj email: ");
        String remail = consoleInput.readLine();
        System.out.print("Podaj hasło: ");
        String haslo = consoleInput.readLine();
        System.out.print("Podaj adres: ");
        String adres = consoleInput.readLine();
        System.out.print("Podaj telefon: ");
        String telefon = consoleInput.readLine();
        output.writeObject(imie);
        output.writeObject(nazwisko);
        output.writeObject(remail);
        output.writeObject(haslo);
        output.writeObject(adres);
        output.writeObject(telefon);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);

        if (responseMessage.equals("SUCCESS")) {
            loggedIn = true;
        }
    }

    private static void handleSelectHotel(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        String choice = "SELECT_HOTEL";
        output.writeObject(choice);
        System.out.print("Dostępne hotele: \n");
        List<Hotel> hotele = (List<Hotel>) input.readObject();
        for (Hotel hotel : hotele) {
            System.out.println(hotel);
        }
        System.out.print("Wybierz hotel: ");
        String nazwaHotelu = consoleInput.readLine();
        output.writeObject(nazwaHotelu);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);

        if (responseMessage.equals("SUCCESS")) {
            hotelSelected = true;
        }
    }

    private static void handleSelectRoomType(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        String choice = "SELECT_ROOM_TYPE";
        output.writeObject(choice);
        System.out.print("Dostępne rodzaje pokoi: \n");
        List<RodzajPokoju> rodzaje = (List<RodzajPokoju>) input.readObject();
        for (RodzajPokoju rodzajPokoju : rodzaje) {
            System.out.println(rodzajPokoju);
        }
        System.out.print("Wybierz rodzaj pokoju: ");
        String rodzajPokoju = consoleInput.readLine();
        output.writeObject(rodzajPokoju);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);

        if (responseMessage.equals("SUCCESS")) {
            roomTypeSelected = true;
        }
    }

    private static void handleCheckRoom(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        String choice = "CHECK_ROOM";
        output.writeObject(choice);
        System.out.println("Podaj datę przyjazdu: ");
        LocalDate dataPrzyjazdu = LocalDate.parse(consoleInput.readLine());
        System.out.println("Podaj datę wyjazdu: ");
        LocalDate dataWyjazdu = LocalDate.parse(consoleInput.readLine());
        output.writeObject(dataPrzyjazdu);
        output.writeObject(dataWyjazdu);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);

        if (responseMessage.equals("Mamy wolny pokój!")) {
            roomAvailabilityChecked = true;
        }
    }

    private static void handleMakeReservation(ObjectOutputStream output, ObjectInputStream input, BufferedReader consoleInput) throws IOException, ClassNotFoundException {
        if (!loggedIn) {
            System.out.println("Musisz być zalogowany lub zarejestrowany, aby dokonać rezerwacji.");
            return;
        }

        String choice = "MAKE_RESERVATION";
        output.writeObject(choice);
        System.out.println("Czy masz jakieś specjalne życzenia? ");
        String uwagi = consoleInput.readLine();
        output.writeObject(uwagi);

        String responseMessage = (String) input.readObject();
        System.out.println("Odpowiedź od serwera: " + responseMessage);
    }
}

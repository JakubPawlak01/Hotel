import Manager.*;
import model.Hotel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    private static UserManager signManager = new UserManager();
    private static HotelManager hotelManager = new HotelManager();
    private static RoomTypeManager roomTypeManager = new RoomTypeManager();
    private static RoomManager roomManager = new RoomManager();
    private static ReservationManager reservationManager = new ReservationManager(roomManager);
    public static int PORT = 5555;

    public Server() {
    }

    public static void main(String[] args) {
        Server server = new Server();

        ExecutorService executorService = Executors.newFixedThreadPool(10);

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Serwer nasłuchuje na porcie " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("Nowy klient połączony: " + clientSocket);

                executorService.submit(new ClientHandler(clientSocket, signManager, hotelManager, roomTypeManager, roomManager, reservationManager));
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }
}

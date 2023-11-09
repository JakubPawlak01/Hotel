package Entity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "rooms")
public class Rooms {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id", nullable = false)
    private Long room_id;

    @Column(name = "hotel_id", nullable = false)
    private Long hotel_id;

    @Column(name = "floor_number", nullable = false)
    private int floor_number;

    @Column(name = "room_number", nullable = false)
    private int room_number;

    @Column(name = "number_of_guests", nullable = false)
    private int number_of_guests;

    @Column(name = "price_per_night", nullable = false)
    private double price_per_night;

    public Rooms(Long room_id, Long hotel_id, int floor_number, int room_number, int number_of_guests, double price_per_night) {
        this.room_id = room_id;
        this.hotel_id = hotel_id;
        this.floor_number = floor_number;
        this.room_number = room_number;
        this.number_of_guests = number_of_guests;
        this.price_per_night = price_per_night;
    }

    public Long getRoom_id() {
        return room_id;
    }

    public void setRoom_id(Long room_id) {
        this.room_id = room_id;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public int getFloor_number() {
        return floor_number;
    }

    public void setFloor_number(int floor_number) {
        this.floor_number = floor_number;
    }

    public int getRoom_number() {
        return room_number;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public int getNumber_of_guests() {
        return number_of_guests;
    }

    public void setNumber_of_guests(int number_of_guests) {
        this.number_of_guests = number_of_guests;
    }

    public double getPrice_per_night() {
        return price_per_night;
    }

    public void setPrice_per_night(double price_per_night) {
        this.price_per_night = price_per_night;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rooms rooms = (Rooms) o;
        return floor_number == rooms.floor_number && room_number == rooms.room_number && number_of_guests == rooms.number_of_guests && Double.compare(price_per_night, rooms.price_per_night) == 0 && Objects.equals(room_id, rooms.room_id) && Objects.equals(hotel_id, rooms.hotel_id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(room_id, hotel_id, floor_number, room_number, number_of_guests, price_per_night);
    }
}

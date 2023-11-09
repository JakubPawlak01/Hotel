package Controller;

import Entity.Rooms;
import Repository.RoomsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class RoomsController {

    private final RoomsRepository roomsRepository;

    @Autowired
    public RoomsController(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @GetMapping
    public List<Rooms> getAllRooms() {
        return roomsRepository.findAll();
    }

    @GetMapping("/{room_id}")
    public Rooms getRoomsById(@PathVariable Long room_id) {
        return roomsRepository.findById(room_id).orElse(null);
    }

    @PostMapping
    public Rooms createRooms(@RequestBody Rooms rooms) {
        return roomsRepository.save(rooms);
    }

    @PutMapping("/{room_id}")
    public Rooms updateRooms(@PathVariable Long room_id, @RequestBody Rooms updatedRooms) {
        Rooms existingRooms = roomsRepository.findById(room_id).orElse(null);
        if (existingRooms != null) {
            existingRooms.setHotel_id(updatedRooms.getHotel_id());
            existingRooms.setFloor_number(updatedRooms.getFloor_number());
            existingRooms.setRoom_number(updatedRooms.getRoom_number());
            existingRooms.setNumber_of_guests(updatedRooms.getNumber_of_guests());
            existingRooms.setPrice_per_night(updatedRooms.getPrice_per_night());
            return roomsRepository.save(existingRooms);
        }
        return null;
    }

    @DeleteMapping("/{room_id}")
    public void deleteRooms(@PathVariable Long room_id) {
        roomsRepository.deleteById(room_id);
    }
}

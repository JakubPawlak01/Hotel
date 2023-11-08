package com.example.demo.Controller;
import com.example.demo.Entity.Hotels;
import com.example.demo.Repository.HotelsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/hotels")
public class HotelsController {

    private final HotelsRepository hotelsRepository;

    @Autowired
    public HotelsController(HotelsRepository hotelsRepository) {
        this.hotelsRepository = hotelsRepository;
    }

    @GetMapping
    public List<Hotels> getAllHotels() {
        return hotelsRepository.findAll();
    }

    @GetMapping("/{hotel_id}")
    public Hotels getHotelById(@PathVariable Long hotel_id) {
        return hotelsRepository.findById(hotel_id).orElse(null);
    }

    @PostMapping
    public Hotels createHotel(@RequestBody Hotels hotel) {
        return hotelsRepository.save(hotel);
    }

    @PutMapping("/{hotel_id}")
    public Hotels updateHotel(@PathVariable Long hotel_id, @RequestBody Hotels updatedHotel) {
        Hotels existingHotel = hotelsRepository.findById(hotel_id).orElse(null);
        if (existingHotel != null) {
            existingHotel.setName(updatedHotel.getName());
            existingHotel.setLocation(updatedHotel.getLocation());
            existingHotel.setFloor_count(updatedHotel.getFloor_count());
            existingHotel.setDescription(updatedHotel.getDescription());
            return hotelsRepository.save(existingHotel);
        }
        return null;
    }

    @DeleteMapping("/{hotel_id}")
    public void deleteHotel(@PathVariable Long hotel_id) {
        hotelsRepository.deleteById(hotel_id);
    }
}

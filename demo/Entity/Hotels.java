package com.example.demo.Entity;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "hotels")
public class Hotels {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hotel_id", nullable = false)
    private Long hotel_id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "floor_count", nullable = false)
    private int floor_count;

    @Column(name = "description", nullable = true)
    private String description;

    public Hotels(Long hotel_id, String name, String location, int floor_count, String description) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.location = location;
        this.floor_count = floor_count;
        this.description = description;
    }

    public Hotels(Long hotel_id, String name, String location, int floor_count) {
        this.hotel_id = hotel_id;
        this.name = name;
        this.location = location;
        this.floor_count = floor_count;
    }

    public Long getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(Long hotel_id) {
        this.hotel_id = hotel_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getFloor_count() {
        return floor_count;
    }

    public void setFloor_count(int floor_count) {
        this.floor_count = floor_count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotels hotels = (Hotels) o;
        return floor_count == hotels.floor_count && Objects.equals(hotel_id, hotels.hotel_id) && Objects.equals(name, hotels.name) && Objects.equals(location, hotels.location) && Objects.equals(description, hotels.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hotel_id, name, location, floor_count, description);
    }
}

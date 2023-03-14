package com.hotel.agency.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Reservation extends AuditEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startDate;
    private LocalDate endDate;
    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Room> rooms = new ArrayList<>();

    @JsonBackReference
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Hotel hotel;

    public Reservation(final LocalDate startDate, final LocalDate endDate, final List<Room> rooms, final Hotel hotel) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.rooms = rooms;
        this.rooms.forEach(room -> room.setReservation(this));
        this.hotel = hotel;
        this.hotel.addReservation(this);
    }

    public void setRooms(final List<Room> rooms) {
        this.rooms = rooms;
        this.rooms.forEach(room -> room.setReservation(this));
    }

    public void setHotel(final Hotel hotel) {
        this.hotel = hotel;
        hotel.addReservation(this);
    }
}

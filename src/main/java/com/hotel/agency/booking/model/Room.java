package com.hotel.agency.booking.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Room extends AuditEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String roomType;
    private int maxAdults;
    private double roomRate;
    private int noOfRooms;
    @JsonBackReference
    @ManyToOne
    private Reservation reservation;

    public Room(final String roomType, final int maxAdults, final double roomRate, final int noOfRooms, final Reservation reservation) {
        this.roomType = roomType;
        this.maxAdults = maxAdults;
        this.roomRate = roomRate;
        this.noOfRooms = noOfRooms;
        this.reservation = reservation;
    }

    public String toString(){
        return this.roomType + " for up-to " + this.maxAdults + " adults";
    }
}

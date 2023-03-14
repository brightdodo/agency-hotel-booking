package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Reservation;
import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.ReservationRepository;
import com.hotel.agency.booking.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final RoomRepository roomRepository;
    public Reservation addReservation(final Reservation reservation) {
        log.info("Creating a new reservation {}", reservation);
        return reservationRepository.save(reservation);
    }

    public List<Reservation> findAll() {
        return reservationRepository.findAll();
    }

    public Reservation findOne(Long id) {
        return reservationRepository.findById(id)
            .orElse(null);
    }

    public Reservation updateReservation(Long id, Reservation reservation){
        return reservationRepository.findById(id)
            .map(reserve -> reservationRepository.save(reservation))
            .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public List<Room> findRoomsInReservation(final Long id) {
        return reservationRepository.findById(id)
            .map(Reservation::getRooms)
            .orElseThrow(EntityNotFoundException::new);
    }

    public Reservation addRoomToReservation(Long reservationId, Room room) {
        return reservationRepository.findById(reservationId)
            .map(reservation -> {
                room.setReservation(reservation);
                roomRepository.save(room);
                reservation.getRooms().add(room);
                return reservationRepository.save(reservation);
            }).orElseThrow();
    }

    public List<Reservation> getCurrentReservations() {
        return reservationRepository
            .findAllByEndDateGreaterThanEqual(LocalDate.now());
    }
}

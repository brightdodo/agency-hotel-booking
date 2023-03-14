package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Reservation;
import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.ReservationRepository;
import com.hotel.agency.booking.repository.RoomRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {
    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private ReservationService reservationService;

    @Test
    void addReservation() {
        when(reservationRepository.save(any())).thenReturn(new Reservation());
        var result = reservationService.addReservation(new Reservation());
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        when(reservationRepository.findAll()).thenReturn(List.of(new Reservation(), new Reservation()));
        var results = reservationService.findAll();
        assertFalse(results.isEmpty());
        assertEquals(2, results.size());
        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void findOne() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(new Reservation()));
        var result = reservationService.findOne(1L);
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Given an invalid Id when findOne reservation should return null")
    void findOne_returnsNull() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());
        var result = reservationService.findOne(1L);
        assertNull(result);
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Given an invalid Id when update reservation should throw exception")
    void update_throwsException() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> reservationService.updateReservation(1L, new Reservation()));
    }

    @Test
    void updateReservation() {
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(new Reservation()));
        when(reservationRepository.save(any())).thenReturn(new Reservation());
        var result = reservationService.updateReservation(1L, new Reservation());
        assertNotNull(result);
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(any());
    }

    @Test
    void deleteReservation() {
        reservationService.deleteReservation(1L);
        verify(reservationRepository, times(1)).deleteById(1L);
    }

    @Test
    void getReservationRooms() {
        var reservation = new Reservation();
        reservation.setRooms(List.of(new Room("ST",1, 650, 1, null)));
        when(reservationRepository.findById(anyLong())).thenReturn(Optional.of(reservation));
        var result = reservationService.findRoomsInReservation(1L);
        assertEquals(1, result.size());
        assertEquals(650, result.get(0).getRoomRate());
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void addRoomToReservation() {
        var room = new Room("ST",1, 650, 1, null);
        when(reservationRepository.findById(anyLong()))
            .thenReturn(Optional.of(new Reservation()));
        when(reservationRepository.save(any())).thenReturn(new Reservation());
        var result = reservationService.addRoomToReservation(1L, room);
        assertNotNull(result);
        verify(reservationRepository, times(1)).save(any());
        verify(roomRepository, times(1)).save(any());
    }

    @Test
    void getCurrentReservations() {
        when(reservationRepository.findAllByEndDateGreaterThanEqual(any())).thenReturn(List.of());
        var result = reservationService.getCurrentReservations();
        assertNotNull(result);
        verify(reservationRepository, times(1)).findAllByEndDateGreaterThanEqual(any());
    }
}
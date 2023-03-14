package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.RoomRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityNotFoundException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class RoomServiceTest {
    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @Test
    void getRooms() {
        when(roomRepository.findAll()).thenReturn(List.of());
        var result = roomService.getRooms();
        assertNotNull(result);
        verify(roomRepository, times(1)).findAll();
    }

    @Test
    void getById() {
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(new Room()));
        var result = roomService.getById(1L);
        assertNotNull(result);
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void givenInvalidId_whenGetById_thenReturnsNull(){
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());
        var result = roomService.getById(1L);
        assertNull(result);
        verify(roomRepository, times(1)).findById(1L);
    }

    @Test
    void addRoom() {
        when(roomRepository.save(any())).thenReturn(new Room());
        var result = roomService.addRoom(new Room());
        assertNotNull(result);
        verify(roomRepository, times(1)).save(any());
    }

    @Test
    void updateRoom() {
        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(new Room()));
        when(roomRepository.save(any())).thenReturn(new Room());
        var result = roomService.updateRoom(1L, new Room());
        assertNotNull(result);
        verify(roomRepository, times(1)).findById(1L);
        verify(roomRepository, times(1)).save(any());
    }

    @Test
    void givenInvalidId_whenUpdateRoom_thenThrowsEntityNotFoundException(){
        when(roomRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> roomService.updateRoom(1L, new Room()));
    }

    @Test
    void deleteRoom() {
        roomService.deleteRoom(1L);
        verify(roomRepository, times(1)).deleteById(1L);
    }
}
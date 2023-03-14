package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Hotel;
import com.hotel.agency.booking.repository.HotelRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class HotelServiceTest {
    @Mock
    private HotelRepository hotelRepository;

    @InjectMocks
    private HotelService hotelService;

    @Test
    void createHotel() {
        when(hotelRepository.save(any())).thenReturn(new Hotel());
        var result = hotelService.createHotel(new Hotel());
        assertNotNull(result);
        verify(hotelRepository, times(1)).save(any());
    }

    @Test
    void findAll() {
        when(hotelRepository.findAll()).thenReturn(List.of(new Hotel(), new Hotel()));
        var results = hotelService.findAll();
        assertNotNull(results);
        assertEquals(2, results.size());
        verify(hotelRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        when(hotelRepository.findById(anyLong())).thenReturn(Optional.of(new Hotel()));
        var result = hotelService.findById(1L);
        assertNotNull(result);
        verify(hotelRepository, times(1)).findById(1L);
    }
}
package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Hotel;
import com.hotel.agency.booking.repository.HotelRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class HotelService {
    private final HotelRepository hotelRepository;

    public Hotel createHotel(final Hotel hotel) {
        return hotelRepository.save(hotel);
    }

    public List<Hotel> findAll() {
        return hotelRepository.findAll();
    }

    public Hotel findById(final Long id) {
        return hotelRepository.findById(id)
            .orElse(null);
    }
}

package com.hotel.agency.booking.service;

import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.RoomRepository;

import org.springframework.stereotype.Service;

import java.util.List;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class RoomService {
    private final RoomRepository repository;
    public List<Room> getRooms() {
        log.info("Fetching all rooms");
        return repository.findAll();
    }

    public Room getById(final Long id) {
        return repository.findById(id).orElse(null);
    }

    public Room addRoom(final Room room) {
        return repository.save(room);
    }

    public Room updateRoom(final Long id, final Room room) {
        return repository.findById(id)
            .map(rm -> repository.save(room))
            .orElseThrow(EntityNotFoundException::new);
    }

    public void deleteRoom(final Long id) {
        repository.deleteById(id);
    }
}

package com.hotel.agency.booking.repository;

import com.hotel.agency.booking.model.Reservation;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findAllByEndDateGreaterThanEqual(LocalDate endDate);
}

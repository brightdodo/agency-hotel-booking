package com.hotel.agency.booking.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
class ReservationRepositoryTest {
    @Autowired
    private ReservationRepository reservationRepository;

    @Test
    @Sql("classpath:/reservations.sql")
    void findAllByEndDateLessThanEqual() {
        var results = reservationRepository.findAllByEndDateGreaterThanEqual(LocalDate.of(2023,3,14));
        assertFalse(results.isEmpty());
        assertEquals(3, results.size());
    }
}
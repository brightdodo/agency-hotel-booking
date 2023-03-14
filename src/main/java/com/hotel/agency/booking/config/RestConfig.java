package com.hotel.agency.booking.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.hotel.agency.booking.exception.NotFoundExceptionHandler;
import com.hotel.agency.booking.resource.HotelResource;
import com.hotel.agency.booking.resource.ReservationResource;
import com.hotel.agency.booking.resource.RoomResource;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;

@Component
public class RestConfig extends ResourceConfig {
    public RestConfig() {
        registerClasses(new HashSet<>(
            List.of(RoomResource.class,
                ReservationResource.class,
                HotelResource.class,
                NotFoundExceptionHandler.class)
        ));
    }

    @Bean
    public ObjectMapper objectMapper() {
        return new ObjectMapper().disable(SerializationFeature.FAIL_ON_EMPTY_BEANS);
    }
}

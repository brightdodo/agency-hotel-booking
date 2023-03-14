package com.hotel.agency.booking.resource;

import com.hotel.agency.booking.model.Hotel;
import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.HotelRepository;
import com.hotel.agency.booking.repository.RoomRepository;
import com.hotel.agency.booking.service.HotelService;
import com.hotel.agency.booking.service.RoomService;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.Application;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class HotelResourceTest extends JerseyTest {
    @Mock
    private HotelService hotelService;

    @InjectMocks
    private HotelResource hotelResource;

    @Override
    protected Application configure() {
        MockitoAnnotations.openMocks(this);
        return new ResourceConfig()
            .register(hotelResource)
            .property("contextConfig", new AnnotationConfigApplicationContext(SpringConfig.class));
    }

    @Configuration
    public static class SpringConfig {
        @Bean
        public HotelService hotelService() {
            return new HotelService(mock(HotelRepository.class));
        }
    }
    @Test
    @DisplayName("Given a valid request when createHotel then return 201 CREATED")
    void create() {
        var request = new Hotel("Blue Moon Hotel", "1 Hotel Road, Sandton");
        Response response = target("/hotel").request()
            .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    @DisplayName("When getHotels then return all hotels 200 OK")
    void getHotels() {
        Response response = target("/hotel").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertNotNull(response.getEntity());
    }

    @Test
    @DisplayName("Given a Valid ID when getHotel then return 200 OK")
    void getHotel() {
        Response response = target("/hotel/1").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }
}
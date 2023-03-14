package com.hotel.agency.booking.resource;

import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.repository.RoomRepository;
import com.hotel.agency.booking.service.RoomService;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class RoomResourceTest extends JerseyTest {
    @Mock
    private RoomService roomService;

    @InjectMocks
    private RoomResource roomResource;

    @Override
    protected Application configure() {
        MockitoAnnotations.openMocks(this);
        return new ResourceConfig()
            .register(roomResource)
            .property("contextConfig", new AnnotationConfigApplicationContext(SpringConfig.class));
    }

    @Configuration
    public static class SpringConfig {
        @Bean
        public RoomService roomService() {
            return new RoomService(mock(RoomRepository.class));
        }
    }

    @Test
    void givenGetRooms_thenResponseIsOK() {
        Response response = target("/hotel/rooms").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(MediaType.APPLICATION_JSON, response.getHeaderString(HttpHeaders.CONTENT_TYPE));
        assertNotNull(response.getEntity());
    }

    @Test
    void givenGetRoom_whenIdIsValid_thenResponseIsOK() {
        Response response = target("/hotel/rooms/1").request().get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void givenAddRoom_whenRequestIsValid_thenResponseIsOK() {
        var request = new Room("Standard", 2, 500.0, 1, null);
        Response response = target("/hotel/rooms").request()
            .post(Entity.entity(request, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.CREATED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void givenUpdateRoom_whenRequestIsValid_thenResponseIsAccepted() {
        var request = new Room("Standard", 2, 500.0, 1, null);
        request.setId(1L);
        Response response = target("/hotel/rooms/1").request()
            .put(Entity.entity(request, MediaType.APPLICATION_JSON));

        assertEquals(Response.Status.ACCEPTED.getStatusCode(), response.getStatus());
        assertNotNull(response.getEntity());
    }

    @Test
    void givenDeleteRoom_whenIdIsValid_thenResponseIsOK() {
        Response response = target("/hotel/rooms/1").request().delete();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
    }
}
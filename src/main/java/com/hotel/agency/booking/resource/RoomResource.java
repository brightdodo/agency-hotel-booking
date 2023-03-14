package com.hotel.agency.booking.resource;


import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.service.RoomService;

import org.springframework.stereotype.Component;

import java.util.List;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/hotel/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@RequiredArgsConstructor
@Slf4j
public class RoomResource {
    private final RoomService roomService;

    @GET
    public Response getRooms() {
        return Response.ok(roomService.getRooms()).build();
    }

    @GET
    @Path("/{id}")
    public Response getRoom(@PathParam("id") Long roomId) {
        return Response.ok(roomService.getById(roomId)).build();
    }

    @POST
    public Response addRoom(Room room) {
        var savedRoom = roomService.addRoom(room);
        log.debug("New room added {}", savedRoom);
        return Response.status(201).entity(savedRoom).build();
    }

    @PUT
    @Path("/{id}")
    public Response updateRoom(@PathParam("id") Long id, Room room) {
        return Response.accepted()
            .entity(roomService.updateRoom(id, room))
            .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteRoom(@PathParam("id") Long id) {
        roomService.deleteRoom(id);
        return Response.ok().build();
    }
}

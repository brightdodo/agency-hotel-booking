package com.hotel.agency.booking.resource;

import com.hotel.agency.booking.model.Reservation;
import com.hotel.agency.booking.model.Room;
import com.hotel.agency.booking.service.ReservationService;

import org.springframework.stereotype.Component;

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

@Path("/hotel/reservations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@RequiredArgsConstructor
public class ReservationResource {
    private final ReservationService reservationService;

    @POST
    public Response addReservation(Reservation reservation) {
        return Response.status(201)
            .entity(reservationService.addReservation(reservation))
            .build();
    }

    @PUT
    @Path("/{id}")
    public Response updateResponse(@PathParam("id") Long id, Reservation reservation) {
        return Response.accepted()
            .entity(reservationService.updateReservation(id, reservation))
            .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteResponse(@PathParam("id") Long id) {
        reservationService.deleteReservation(id);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    public Response getReservation(@PathParam("id") Long id) {
        return Response.ok(reservationService.findOne(id)).build();
    }

    @GET
    public Response getReservations() {
        return Response.ok(reservationService.findAll()).build();
    }

    @GET
    @Path("/{id}/rooms")
    public Response getReservationRooms(@PathParam("id") Long id) {
        return Response.ok(reservationService.findRoomsInReservation(id)).build();
    }

    @POST
    @Path("/{id}/rooms")
    public Response addRoomToReservation(@PathParam("id") Long id, Room room) {
        return Response.ok(reservationService.addRoomToReservation(id, room)).build();
    }

    @GET
    @Path("/current")
    public Response getAllCurrentReservations(){
        return Response.ok(reservationService.getCurrentReservations())
            .build();
    }
}

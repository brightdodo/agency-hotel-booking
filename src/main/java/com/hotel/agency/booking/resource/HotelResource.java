package com.hotel.agency.booking.resource;

import com.hotel.agency.booking.model.Hotel;
import com.hotel.agency.booking.service.HotelService;

import org.springframework.stereotype.Component;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Path("/hotel")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Component
@RequiredArgsConstructor
@Slf4j
public class HotelResource {
    private final HotelService hotelService;

    @POST
    public Response create(Hotel hotel) {
        return Response.status(201).entity(hotelService.createHotel(hotel)).build();
    }

    @GET
    public Response getHotels() {
        return Response.ok(hotelService.findAll()).build();
    }

    @GET
    @Path("/{id}")
    public Response getHotel(@PathParam("id") Long id){
        return Response.ok(hotelService.findById(id)).build();
    }

}

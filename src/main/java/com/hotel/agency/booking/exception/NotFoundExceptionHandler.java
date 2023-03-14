package com.hotel.agency.booking.exception;

import jakarta.persistence.EntityNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionHandler implements ExceptionMapper<EntityNotFoundException> {
    @Override
    public Response toResponse(final EntityNotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).entity(
            ErrorResponse.builder()
                .status(404)
                .message(e.getMessage())
        ).build();
    }
}

package org.quarkusclub.models.exceptions;

import jakarta.inject.Inject;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;
import org.jboss.logging.Logger;

@Provider
public class GlobalExceptionHandler implements ExceptionMapper<Exception> {

    @Inject
    Logger log;

    @Override
    public Response toResponse(Exception e) {
        log.info("Tratamento de erro global: " + e.toString());

        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorModel(Response.Status.INTERNAL_SERVER_ERROR, e.getMessage()))
                .build();
    }
}
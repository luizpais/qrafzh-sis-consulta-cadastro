package org.quarkusclub.models.exceptions;

import jakarta.ws.rs.core.Response;

public class SisConsultaException extends Exception {

        private static final long serialVersionUID = 1L;
        private Response.Status status = Response.Status.INTERNAL_SERVER_ERROR;

        public SisConsultaException(String message) {
            super(message);
        }

        public SisConsultaException(String message, Response.Status newStatus) {
            super(message);
            status = newStatus;
        }

        public Response.Status getStatus() {
            return status;
        }
}

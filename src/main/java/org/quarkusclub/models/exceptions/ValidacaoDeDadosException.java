package org.quarkusclub.models.exceptions;

import jakarta.ws.rs.core.Response;

public class ValidacaoDeDadosException extends SisConsultaException {

    public ValidacaoDeDadosException(String message, Response.Status newStatus) {
        super(message, newStatus);
    }
}

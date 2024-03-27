package org.quarkusclub.models.exceptions;

import jakarta.ws.rs.core.Response;

public class ClienteNaoCadastradoException extends SisConsultaException {

    public ClienteNaoCadastradoException(String message) {
        super(message, Response.Status.NOT_FOUND);
    }
}

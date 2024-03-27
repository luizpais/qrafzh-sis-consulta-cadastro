package org.quarkusclub.models;

import jakarta.ws.rs.core.Response;
import lombok.Data;

@Data
public class ErrorModel {
    private Response.Status status;
    private String message;

    public ErrorModel(Response.Status status, String message) {
        this.status = status;
        this.message = message;
    }

    // getters and setters
}
package org.univaq.swa.template.exceptions;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import java.sql.SQLException;

public class RESTWebApplicationException extends WebApplicationException {

    public RESTWebApplicationException(SQLException ex) {
        super(Response.serverError().build());
    }

    public RESTWebApplicationException(String message) {
        super(Response.serverError()
                .entity(message)
                .type("text/plain")
                .build());
    }

    public RESTWebApplicationException(int status, String message) {
        super(Response.status(status)
                .entity(message)
                .type("text/plain")
                .build());
    }
}

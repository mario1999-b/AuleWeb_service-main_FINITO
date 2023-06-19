package org.univaq.swa.template.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import org.univaq.swa.model.Aula;
import org.univaq.swa.model.Evento;
import org.univaq.swa.template.exceptions.RESTWebApplicationException;

import java.sql.*;
import java.time.LocalDate;

@Path("evento")
public class EventoRes {
    Class c = Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aule_web", "postgres", "root");

    public EventoRes() throws ClassNotFoundException, SQLException {
    }

    /**
     * OP. 9 Lettura informazioni di un evento
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getInformazioniEvento(@Context UriInfo uriInfo, @PathParam("id") int id, @Context ContainerRequestContext requestContext){
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM evento WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomeEvento = rs.getString("nomeEvento");
                String descrizioneEvento = rs.getString("descrizioneEvento");
                String nomeResponsabile = rs.getString("nomeResponsabile");
                String emailResponsabile = rs.getString("emailResponsabile");
                LocalDate dataEvento = rs.getDate("dataEvento").toLocalDate();
                Evento evento = new Evento(id,nomeEvento,descrizioneEvento,nomeResponsabile,emailResponsabile,dataEvento); //TODO ERRORE DI TIPO IN LOCALDATE, RISOLVERE!
                return Response.ok(evento).build();
            }
        } catch (SQLException ex) {
            System.out.println("Errore"+ex.getMessage());
        }
        return Response.serverError().build();
    }
}

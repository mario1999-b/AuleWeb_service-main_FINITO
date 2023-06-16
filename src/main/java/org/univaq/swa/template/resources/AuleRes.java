package org.univaq.swa.template.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.core.UriInfo;
import org.univaq.swa.template.exceptions.RESTWebApplicationException;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("aule")
public class AuleRes {
    Class c = Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aule_web","postgres","root");

    public AuleRes() throws ClassNotFoundException, SQLException {
    }

    static Map<String, Object> createAula(ResultSet rs) {
        try {
            Map<String, Object> aula = new LinkedHashMap<>();
            aula.put("id", rs.getInt("id"));
            aula.put("nomeAula", rs.getString("nomeAula"));
            aula.put("luogo", rs.getString("luogo"));
            aula.put("piano", rs.getString("piano"));
            aula.put("emailResponsabile", rs.getString("emailResponsabile"));
            aula.put("capienza", rs.getString("capienza"));
            aula.put("edificio", rs.getString("edificio"));

            return aula;
        } catch (SQLException ex) {
            throw new RESTWebApplicationException(ex);
        }
    }

    private Response auleUriList(@Context UriInfo uriInfo, String username, List<String> aule, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, username);
        try (ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
               aule.add(uriInfo.getBaseUriBuilder()
                        .path(AuleRes.class)
                        .path(AuleRes.class, "getAule")
                        .build(rs.getInt("id")).toString());
            }
            if (aule.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).build();
            } else {
                return Response.ok(aule).build();
            }
        }
    }
    @GET
    @Path("/CSV")
    @Produces("application/json")
    public Response getCSVaule(@Context UriInfo uriInfo, @Context SecurityContext securityContext) {
        List<String> aule = new ArrayList<>();
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM aula")) {
            return auleUriList(uriInfo, securityContext.getUserPrincipal().getName(), aule, stmt);
        } catch (SQLException ex) {
            throw new RESTWebApplicationException(ex);
        }
    }

    /*@GET
    @Path("CSV")
    @Produces("text/csv")
    public Response getCSVaule( ){

        return null;
    }
    */
}

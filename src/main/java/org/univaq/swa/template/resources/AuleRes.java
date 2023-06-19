package org.univaq.swa.template.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;
import org.univaq.swa.model.Attrezzature;
import org.univaq.swa.model.Aula;
import org.univaq.swa.template.exceptions.RESTWebApplicationException;

import java.net.URI;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Path("aule")
public class AuleRes {
    Class c = Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aule_web", "postgres", "root");

    public AuleRes() throws ClassNotFoundException, SQLException {
    }

    /**
     * OP.5 GET DELLE INFORMAZIONI DI BASE DELL'AULA
     *
     * @param
     * @return
     */
    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Response getInfAula(@Context UriInfo uriInfo, @PathParam("id") int id, @Context ContainerRequestContext requestContext) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM aula WHERE id = ?")) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                String nomeAula = rs.getString("nomeAula");
                Aula aula = new Aula(id, nomeAula);
                return Response.ok(aula).build();
            }
        } catch (SQLException ex) {
            throw new RESTWebApplicationException(ex);
        }
        return null;
    }

    /**
     * OP.6 GET ATTREZZATURE DELL'AULA
     *
     * @param
     * @return
     */
    @GET
    @Path("/{id_aula}/attrezzature")
    @Produces("application/json")
    public Response getListaAttAula(@Context UriInfo uriInfo, @PathParam("id_aula") int id_aula, @Context ContainerRequestContext requestContext) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM aula JOIN attrezzature ON aula.id = attrezzature.id WHERE aula.id = ? ")) {
            stmt.setInt(1,id_aula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()){
                int id = rs.getInt("id");
                int numeroSedie = rs.getInt("n_sedie");
                int numeroTavoli = rs.getInt("n_tavoli");
                int numeroLavagne = rs.getInt("n_Lavagne");
                Attrezzature attrezzature = new Attrezzature(id,numeroSedie, numeroTavoli, numeroLavagne);
                return Response.ok(attrezzature).build();
            }
            return Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * OP.3 INSERIMENTO NUOVA AULA
     * @param
     * @return
     */

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response inserimentoAula(@Context UriInfo uriInfo,Map<String, Object> aula, @Context ContainerRequestContext requestContext){
        String query ="INSERT INTO aula("+
             " \"nomeAula\", luogo, piano, \"emailResponsabile\", gruppo, \"id_attrezzature\")" +
         "VALUES ( ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query,new String[]{"id"})) {
            stmt.setString(1, (String) aula.get("nomeAula"));
            stmt.setString(2, (String) aula.get("luogo"));
            stmt.setString(3, (String) aula.get("piano"));
            stmt.setString(4, (String) aula.get("emailResponsabile"));
            stmt.setString(5, (String) aula.get("gruppo"));
            stmt.setString(6, (String) aula.get("id_attrezzature"));
            int i = stmt.executeUpdate();
            if (i >= 1) {
                try (ResultSet rs = stmt.getGeneratedKeys()) {
                    rs.next();
                    int id_aula = rs.getInt("id");
                    URI uri = uriInfo.getBaseUriBuilder().path("aule/" + id_aula).build();
                    return Response.created(uri).build();
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Response.serverError().build();
    }


    /*static Map<String, Object> createAula(ResultSet rs) {
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
   */
   /* private Response auleUriList(@Context UriInfo uriInfo, String username, List<String> aule, PreparedStatement stmt) throws SQLException {
        stmt.setString(1, username);
        // TODO AGGIUNGI CONTROLLO UTENTE LOGGATO
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
    */

  /*  @GET
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
    */
    /*@GET
    @Path("CSV")
    @Produces("text/csv")
    public Response getCSVaule( ){

        return null;
    }
    */
}

package org.univaq.swa.template.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.core.*;
import org.univaq.swa.model.Attrezzature;
import org.univaq.swa.model.Aula;
import org.univaq.swa.model.Gruppo;
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
                String nomeAula = rs.getString("nome_aula");
                Integer idGruppo = rs.getInt("id_gruppo");

                Aula aula = new Aula(id, nomeAula, idGruppo);
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
            // TODO MODIFICA QUERY PER FAR ASSEGNARE ID DI AULE DIVERSI, AD ATTREZZATURE DIVERSE
            stmt.setInt(1, id_aula);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int numeroSedie = rs.getInt("n_sedie");
                int numeroTavoli = rs.getInt("n_tavoli");
                int numeroLavagne = rs.getInt("n_Lavagne");
                Attrezzature attrezzature = new Attrezzature(id, numeroSedie, numeroTavoli, numeroLavagne);
                return Response.ok(attrezzature).build();
            }
            return Response.serverError().build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * OP.3 INSERIMENTO NUOVA AULA
     *
     * @param
     * @return
     */

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response inserimentoAula(@Context UriInfo uriInfo, Map<String, Object> aula, @Context ContainerRequestContext requestContext) {
        String query = "INSERT INTO aula(" +
                "nome_aula, luogo, piano,email_responsabile,id_attrezzature,id_gruppo)" +
                "VALUES ( ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, (String) aula.get("nome"));
            stmt.setString(2, (String) aula.get("luogo"));
            stmt.setString(3, (String) aula.get("piano"));
            stmt.setString(4, (String) aula.get("emailResponsabile"));
            stmt.setInt(5, Integer.valueOf((String) aula.get("idAttrezzature")));
            if (aula.containsKey("idGruppo")) {
                String idGruppo = (String) aula.get("idGruppo");
                if (idGruppo != null && !idGruppo.isBlank()) {
                    stmt.setInt(6, Integer.valueOf(idGruppo));
                } else {
                    stmt.setNull(6, Types.NUMERIC);
                }
            }
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

    /**
     * OP.2 ESPORTAZIONE CSV
     */
    @GET
    @Path("/CSV")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getCsvAule(@Context UriInfo uriInfo, @Context ContainerRequestContext requestContext) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT * FROM aula ORDER BY id")) {
            ResultSet rs = stmt.executeQuery();
            List<String> results = new ArrayList<>();
            while (rs.next()) {
                int idAula = rs.getInt("id");
                String nomeAula = rs.getString("nome_aula");
                String luogo = rs.getString("luogo");
                String piano = rs.getString("piano");
                String emailResponsabile = rs.getString("email_responsabile");
                int idGruppo = rs.getInt("id_gruppo");
                int idAttrezzature = rs.getInt("id_attrezzature");
                Aula aula = new Aula(idAula, nomeAula, luogo, piano, emailResponsabile, idGruppo, idAttrezzature);
                results.add(aula.toString());
            }
            return Response.ok(String.join("\n", results)).build();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    // TODO AGGIUNGI CONTROLLO UTENTE LOGGATO

    /**
     * OP.4 ASSEGNAZIONE AULA AD UN GRUPPO
     */
    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/{id_aula}/gruppo/{id_gruppo}")
    public Response putAulaToGruppo(@Context UriInfo uriInfo, @PathParam("id_aula") int idAula, @PathParam("id_gruppo") int idGruppo, Map<String, Object> aula, @Context ContainerRequestContext requestContext) {
        String query = "UPDATE aula" +
                "SET id=?, nome_aula=?, luogo=?, piano=?, email_responsabile=?, id_attrezzature=?, id_gruppo=? WHERE id = ? and id_gruppo = ?;";
        try (PreparedStatement stmt = con.prepareStatement(query)){ // TODO CORREGGI E FAI FUNZIONARE LA PUT
           stmt.setInt(0, idAula);
           stmt.setInt(6,idGruppo);
           ResultSet rs = stmt.executeQuery();
           while (rs.next()){
               stmt.setString(1, (String) aula.get("nome"));
               stmt.setString(2, (String) aula.get("luogo"));
               stmt.setString(3, (String) aula.get("piano"));
               stmt.setString(4, (String) aula.get("emailResponsabile"));
               stmt.setInt(5, Integer.valueOf((String) aula.get("idAttrezzature")));
               return Response.status(Response.Status.NO_CONTENT).build();
           }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Response.serverError().build();
    }
}

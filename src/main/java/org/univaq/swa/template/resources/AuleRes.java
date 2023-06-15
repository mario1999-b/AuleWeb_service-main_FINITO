package org.univaq.swa.template.resources;

import jakarta.ws.rs.Path;
import org.univaq.swa.template.exceptions.RESTWebApplicationException;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;

@Path("aule")
public class AuleRes {
    Class c = Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aule_web/","postegre","root");

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
}

package org.univaq.swa.framework.security;

import jakarta.ws.rs.core.UriInfo;

import java.sql.*;
import java.util.UUID;
import java.util.logging.Logger;

/**
 *
 * Una classe di utilità di supporto all'autenticazione
 * qui tutto è finto, non usiamo JWT o altre tecnologie
 *
 */
public class AuthHelpers {
    Class c = Class.forName("org.postgresql.Driver");
    Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/aule_web","postgres","root");

    private static AuthHelpers instance = null;

    public AuthHelpers() throws ClassNotFoundException, SQLException {

    }

    public boolean authenticateUser(String username, String password) {
        try (PreparedStatement stmt = con.prepareStatement("SELECT id FROM boss WHERE username = ? AND password = ?")) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            try (ResultSet rs = stmt.executeQuery()) {
                rs.next();
                return true;
            }
        } catch (SQLException e) {
            return false;
        }
    }

    public String issueToken(UriInfo context, String username) {        
        String token = username + UUID.randomUUID().toString();
        return token;
    }

    public void revokeToken(String token) {
        /* invalidate il token */
        try (PreparedStatement stmt = con.prepareStatement("UPDATE boss SET token = NULL WHERE token = ?")) {
            stmt.setString(1, token);
            stmt.executeUpdate();
        } catch (SQLException e) {
            Logger.getLogger(AuthenticationRes.class.getName()).severe(e.getMessage());
        }
    }

    public String validateToken(String token) {
        return "pippo"; //lo username andrebbe derivato dal token!
    }

    public static AuthHelpers getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            instance = new AuthHelpers();
        }
        return instance;
    }

}

package Controlador;

import Modelo.Usuario;
import db.ConexionDB;

import java.sql.*;

public class UsuarioControlador {

    public boolean registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuario (username, password) VALUES (?, ?)";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, usuario.getUsername());
            stmt.setString(2, usuario.getPassword());

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public Usuario verificarCredenciales(String username, String password) throws SQLException {
        String query = "SELECT * FROM usuario WHERE username = ? AND password = ?";

        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                int userId = rs.getInt("usuario_id");
                String userUsername = rs.getString("username");
                String userPassword = rs.getString("password");

                return new Usuario(userId, userUsername, userPassword);
            } else {
                return null;
            }
        }
    }


    public Usuario login(String username, String password) {
        String sql = "SELECT * FROM usuario WHERE username = ? AND password = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("username"), rs.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
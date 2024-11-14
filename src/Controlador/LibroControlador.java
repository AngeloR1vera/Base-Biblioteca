package Controlador;

import Modelo.Categoria;
import Modelo.Libro;
import db.ConexionDB;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibroControlador {

    public void agregarLibro(Libro libro) throws SQLException {
        Connection conn = ConexionDB.getConexion();
        PreparedStatement psLibro = null;
        ResultSet rs = null;

        try {
            String sqlLibro = "INSERT INTO libro (elemento_id, libro_id, titulo, anio_publicacion, nro_isbn, categoria_categoria_id, editorial_editorial_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
            psLibro = conn.prepareStatement(sqlLibro, Statement.RETURN_GENERATED_KEYS);
            psLibro.setInt(1, libro.getElementoId());
            psLibro.setInt(2, libro.getLibroId());
            psLibro.setString(3, libro.getTitulo());
            psLibro.setInt(4, libro.getAnioPublicacion());
            psLibro.setString(5, libro.getIsbn());
            psLibro.setInt(6, libro.getCategoriaId());
            psLibro.setInt(7, libro.getEditorialId());


            psLibro.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (psLibro != null) psLibro.close();
                if (rs != null) rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


    public List<Libro> obtenerTodosLosLibros() {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libro";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("elemento_id"),
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("isbn"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("autor")
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public void eliminarLibro(int id) {
        String sql = "DELETE FROM libro WHERE id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Libro> buscarLibro(String criterio) {
        List<Libro> libros = new ArrayList<>();
        String sql = "SELECT * FROM libro WHERE titulo LIKE ? OR isbn LIKE ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + criterio + "%");
            stmt.setString(2, "%" + criterio + "%");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Libro libro = new Libro(
                        rs.getInt("elemento_id"),
                        rs.getInt("libro_id"),
                        rs.getString("titulo"),
                        rs.getString("isbn"),
                        rs.getInt("anio_publicacion"),
                        rs.getString("autor")
                );
                libros.add(libro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return libros;
    }

    public boolean categoriaExiste(int categoriaId) throws SQLException {
        String sql = "SELECT 1 FROM categoria WHERE categoria_id = ?";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        }
    }

    public List<Categoria> obtenerCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sql = "SELECT categoria_id, nombre_categoria FROM categoria";
        try (Connection conn = ConexionDB.getConexion();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int categoriaId = rs.getInt("categoria_id");
                String nombreCategoria = rs.getString("nombre_categoria");
                categorias.add(new Categoria(categoriaId, nombreCategoria));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }

    public Categoria obtenerCategoriaIdPorNombre(String categoriaNombre) throws SQLException {
        String query = "SELECT categoria_id, nombre_categoria FROM categoria WHERE nombre_categoria = ?";

        try (Connection connection = ConexionDB.getConexion();
             PreparedStatement statement = connection.prepareStatement(query)) {

            statement.setString(1, categoriaNombre);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                int categoriaId = resultSet.getInt("categoria_id");
                String nombreCategoria = resultSet.getString("nombre_categoria");
                return new Categoria(categoriaId, nombreCategoria);
            } else {
                throw new SQLException("Categoría no encontrada");
            }
        } catch (SQLException e) {
            throw new SQLException("Error al obtener el id de la categoría: " + e.getMessage(), e);
        }
    }



    public void insertarCategorias() throws SQLException {
        String[] categorias = {"Desarrollo de Software", "Matemáticas", "Ciencia", "Historia"};
        Connection conn = ConexionDB.getConexion();
        PreparedStatement stmt = null;

        try {
            String sql = "INSERT INTO categoria (nombre_categoria) VALUES (?)";
            stmt = conn.prepareStatement(sql);

            for (String categoria : categorias) {
                stmt.setString(1, categoria);
                stmt.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (stmt != null) stmt.close();
        }
    }
}
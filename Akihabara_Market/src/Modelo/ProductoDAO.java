package Modelo;

import java.sql.*;
import java.util.*;

public class ProductoDAO {

    public void agregarProducto(ProductoOtaku p) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "INSERT INTO productos (nombre, categoria, precio, descripcion, stock) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPrecio());
            stmt.setString(4, p.getDescripcion());
            stmt.setInt(5, p.getStock());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ProductoOtaku obtenerProductoPorId(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productos WHERE id = ?")) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getString("descripcion"),
                        rs.getInt("stock")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<ProductoOtaku> obtenerTodosLosProductos() {
        List<ProductoOtaku> productos = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM productos");
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                productos.add(new ProductoOtaku(
                        rs.getInt("id"),
                        rs.getString("nombre"),
                        rs.getString("categoria"),
                        rs.getDouble("precio"),
                        rs.getString("descripcion"),
                        rs.getInt("stock")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }
    
    public String generarResumenProductos() {
        StringBuilder resumen = new StringBuilder("Lista de productos en inventario:\n\n");
        List<ProductoOtaku> productos = obtenerTodosLosProductos();
        for (ProductoOtaku p : productos) {
            resumen.append("- ")
                   .append(p.getNombre()).append(" (")
                   .append(p.getCategoria()).append("): ")
                   .append(p.getPrecio()).append("€, stock: ")
                   .append(p.getStock()).append("\n");
        }
        return resumen.toString();
    }

    public boolean actualizarProducto(ProductoOtaku p) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(
                     "UPDATE productos SET nombre=?, categoria=?, precio=?, descripcion=?, stock=? WHERE id=?")) {

            stmt.setString(1, p.getNombre());
            stmt.setString(2, p.getCategoria());
            stmt.setDouble(3, p.getPrecio());
            stmt.setString(4, p.getDescripcion());
            stmt.setInt(5, p.getStock());
            stmt.setInt(6, p.getId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean eliminarProducto(int id) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM productos WHERE id = ?")) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}

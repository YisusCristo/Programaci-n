package Paquete;

import java.sql.*;

public class PeliculaDAO {
    
    private Connection conexion;
    
    public PeliculaDAO(Connection conexion) {
        this.conexion = conexion;
    }
    

//Obtiene todas las películas junto con su información relacionada de géneros y salas junto a la información formateada por pantalla

    public void mostrarPeliculas() {
        String consulta = "SELECT p.id_pelicula, p.titulo, p.director, p.duracion_minutos, " + "p.fecha_estreno, g.nombre AS genero, g.descripcion AS desc_genero, " + "s.nombre AS sala, s.capacidad, s.tiene_3d " + "FROM peliculas p " + "JOIN generos g ON p.id_genero = g.id_genero " + "JOIN salas s ON p.id_sala = s.id_sala " + "ORDER BY p.titulo";
        
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(consulta)) {
            
            if (!rs.isBeforeFirst()) {
                System.out.println("No hay películas disponibles en este momento.");
                return;
            }
            
            System.out.println("\n PELÍCULAS EN CARTELERA \n");
            System.out.printf("ID", "TÍTULO", "DIRECTOR", "DURACIÓN", "ESTRENO", "GÉNERO", "SALA");
            
            while (rs.next()) {
                String id = rs.getString("id_pelicula");
                String titulo = rs.getString("titulo");
                String director = rs.getString("director");
                int duracion = rs.getInt("duracion_minutos");
                Date estreno = rs.getDate("fecha_estreno");
                String genero = rs.getString("genero");
                String sala = rs.getString("sala");
                
                System.out.printf(id, titulo, director, duracion, estreno, genero, sala);
                
                // Mostrar información adicional
                boolean es3D = rs.getBoolean("tiene_3d");
                int capacidad = rs.getInt("capacidad");
                String descGenero = rs.getString("desc_genero");
                
                System.out.printf("        Sala %s: Capacidad %d personas, %s%n", sala, capacidad, es3D ? "Proyección en 3D disponible" : "Proyección estándar");
                System.out.printf("        Género: %s - %s%n", genero, descGenero);
                System.out.println();
            }
            
        } catch (SQLException e) {
            System.err.println("Error al obtener las películas: " + e.getMessage());
        }
    }
}

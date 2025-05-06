package Paquete;

import java.sql.*;

public class ConexionBD {
    
    // Constantes para la conexión a la base de datos
    private static final String URL = "jdbc:mysql://localhost:3306/cine_jesussanchez";
    private static final String USUARIO = "root";
    private static final String CONTRASEÑA = "rook"; // Actualizar según tu contraseña
    
    
    public static Connection obtenerConexion() {
        Connection conexion = null;
        
        try {
            // Cargar MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establecer la conexión
            conexion = DriverManager.getConnection(URL, USUARIO, CONTRASEÑA);
            
            System.out.println("Conexión establecida con éxito a la base de datos.");
            
        } catch (ClassNotFoundException e) {
            System.err.println("Error: No se encontró el driver de MySQL: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
        
        return conexion;
    }
    
    
    public static void cerrarConexion(Connection conexion) {
        if (conexion != null) {
            try {
                conexion.close();
                System.out.println("Conexión cerrada correctamente.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}

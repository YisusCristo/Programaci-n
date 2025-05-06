package Paquete;

import java.sql.Connection;

public class CineApp {
    
    public static void main(String[] args) {
        // Obtener conexión a la base de datos
        Connection conexion = ConexionBD.obtenerConexion();
        
        if (conexion != null) {
            // Crear y mostrar el menú principal
            Menu menu = new Menu(conexion);
            menu.mostrarMenu();
            
            // Cerrar recursos
            menu.cerrarScanner();
            ConexionBD.cerrarConexion(conexion);
        } else {
            System.out.println("No se pudo conectar a la base de datos. La aplicación va a cerrarse.");
        }
    }
}

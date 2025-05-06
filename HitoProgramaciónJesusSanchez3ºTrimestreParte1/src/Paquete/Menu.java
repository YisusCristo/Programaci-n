package Paquete;

import java.util.Scanner;
import java.sql.Connection;

public class Menu {
    
    private Scanner scanner;
    private PeliculaDAO peliculaDAO;

    public Menu(Connection conexion) {
        scanner = new Scanner(System.in);
        peliculaDAO = new PeliculaDAO(conexion);
    }
    
     //Muestra el menú principal de la aplicación y procesa la opción seleccionada

    public void mostrarMenu() {
        int opcion;
        boolean salir = false;
        
        while (!salir) {
            System.out.println("\n GESTIÓN DE PELÍCULAS DE CINE ");
            System.out.println("1 - Ver películas");
            System.out.println("2 - Salir");
            System.out.print("Seleccione una opción: ");
            
            try {
                opcion = Integer.parseInt(scanner.nextLine());
                
                switch (opcion) {
                    case 1:
                        peliculaDAO.mostrarPeliculas();
                        break;
                    case 2:
                        salir = true;
                        System.out.println("Gracias por usar mi aplicación!");
                        break;
                    default:
                        System.out.println("Opción no válida. Intentalo de nuevo.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, ingrese un número funcional.");
            }
        }
    }
    
     //Cierra el scanner cuando ya no se necesita
    public void cerrarScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}
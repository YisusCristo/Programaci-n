package Vista;

import Modelo.ClienteOtaku;
import Modelo.ProductoOtaku;

import java.util.List;
import java.util.Scanner;

public class InterfazConsola {
    private Scanner scanner = new Scanner(System.in);

    // ======== Menú Principal ========
    public int mostrarMenuPrincipal() {
        System.out.println("\n--- Akihabara Market ---");
        System.out.println("1. Añadir producto");
        System.out.println("2. Consultar producto por ID");
        System.out.println("3. Ver todos los productos");
        System.out.println("4. Actualizar producto");
        System.out.println("5. Eliminar producto");
        System.out.println("6. Asistente IA");
        System.out.println("7. Gestión de clientes");
        System.out.println("8. Salir");
        System.out.print("Selecciona una opción: ");
        return scanner.nextInt();
    }

    // ======== Menú Clientes ========
    public int mostrarMenuClientes() {
        System.out.println("\n--- Gestión de Clientes ---");
        System.out.println("1. Añadir nuevo cliente");
        System.out.println("2. Consultar cliente por ID");
        System.out.println("3. Ver todos los clientes");
        System.out.println("4. Actualizar cliente");
        System.out.println("5. Eliminar cliente");
        System.out.println("6. Volver al menú principal");
        System.out.print("Selecciona una opción: ");
        return scanner.nextInt();
    }

    // ======== Productos ========
    public ProductoOtaku solicitarDatosProducto() {
        scanner.nextLine(); // limpiar buffer
        System.out.print("Nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.print("Categoría: ");
        String categoria = scanner.nextLine();

        System.out.print("Precio: ");
        double precio = scanner.nextDouble();
        scanner.nextLine(); // limpiar enter

        System.out.print("Descripción: ");
        String descripcion = scanner.nextLine();

        System.out.print("Stock: ");
        int stock = scanner.nextInt();
        scanner.nextLine(); // limpiar enter final

        return new ProductoOtaku(nombre, categoria, precio, descripcion, stock);
    }

    public int solicitarIdProducto() {
        System.out.print("Ingresa el ID del producto: ");
        return scanner.nextInt();
    }

    public void mostrarProductos(List<ProductoOtaku> productos) {
        for (ProductoOtaku p : productos) {
            System.out.println("ID: " + p.getId()
                    + ", Nombre: " + p.getNombre()
                    + ", Categoría: " + p.getCategoria()
                    + ", Precio: $" + p.getPrecio()
                    + ", Descripción: " + p.getDescripcion()
                    + ", Stock: " + p.getStock());
        }
    }

    // ======== Clientes ========
    public ClienteOtaku solicitarDatosCliente() {
        scanner.nextLine(); // limpiar buffer
        System.out.print("Nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.print("Email: ");
        String email = scanner.nextLine();

        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();

        return new ClienteOtaku(nombre, email, telefono);
    }

    public int solicitarIdCliente() {
        System.out.print("Ingresa el ID del cliente: ");
        return scanner.nextInt();
    }

    public void mostrarClientes(List<ClienteOtaku> clientes) {
        for (ClienteOtaku c : clientes) {
            mostrarCliente(c);
        }
    }

    public void mostrarCliente(ClienteOtaku c) {
        System.out.println("ID: " + c.getId()
                + ", Nombre: " + c.getNombre()
                + ", Email: " + c.getEmail()
                + ", Teléfono: " + c.getTelefono()
                + ", Fecha registro: " + c.getFechaRegistro());
    }

    // ======== Mensajes generales ========
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }
}

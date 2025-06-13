package Controlador;

import Modelo.ProductoDAO;
import Modelo.ProductoOtaku;
import Vista.InterfazConsola;

import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        InterfazConsola vista = new InterfazConsola();
        ProductoDAO productoDAO = new ProductoDAO();

        boolean salir = false;
        while (!salir) {
            int opcion = vista.mostrarMenuPrincipal();
            switch (opcion) {
                case 1: // Crear producto
                    ProductoOtaku nuevo = vista.solicitarDatosProducto();
                    productoDAO.agregarProducto(nuevo);
                    vista.mostrarMensaje("Producto agregado correctamente.");
                    break;

                case 2: // Consultar por ID
                    int idConsulta = vista.solicitarIdProducto();
                    ProductoOtaku encontrado = productoDAO.obtenerProductoPorId(idConsulta);
                    if (encontrado != null) {
                        vista.mostrarMensaje("Producto encontrado:");
                        vista.mostrarProductos(List.of(encontrado));
                    } else {
                        vista.mostrarMensaje("Producto no encontrado.");
                    }
                    break;

                case 3: // Ver todos
                    List<ProductoOtaku> lista = productoDAO.obtenerTodosLosProductos();
                    if (lista.isEmpty()) {
                        vista.mostrarMensaje("No hay productos registrados.");
                    } else {
                        vista.mostrarProductos(lista);
                    }
                    break;

                case 4: // Actualizar producto
                    int idActualizar = vista.solicitarIdProducto();
                    ProductoOtaku actualizado = vista.solicitarDatosProducto();
                    actualizado.setId(idActualizar);
                    if (productoDAO.actualizarProducto(actualizado)) {
                        vista.mostrarMensaje("Producto actualizado correctamente.");
                    } else {
                        vista.mostrarMensaje("No se pudo actualizar (ID no encontrado).");
                    }
                    break;

                case 5: // Eliminar producto
                    int idEliminar = vista.solicitarIdProducto();
                    if (productoDAO.eliminarProducto(idEliminar)) {
                        vista.mostrarMensaje("Producto eliminado correctamente.");
                    } else {
                        vista.mostrarMensaje("No se pudo eliminar (ID no encontrado).");
                    }
                    break;

                case 6: // Asistente IA (puedes agregar integración aquí)
                    vista.mostrarMensaje("Funcionalidad IA aún no implementada.");
                    break;

                case 7: // Gestión de clientes (puedes implementar otro submenú)
                    vista.mostrarMensaje("Menú de clientes aún no implementado.");
                    break;

                case 8: // Salir
                    salir = true;
                    vista.mostrarMensaje("¡Gracias por usar Akihabara Market!");
                    break;

                default:
                    vista.mostrarMensaje("Opción no válida.");
            }
        }
    }
}

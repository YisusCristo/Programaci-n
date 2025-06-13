package Vista;

import Modelo.ProductoOtaku;
import Modelo.ProductoDAO;
import Modelo.LlmService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class ProductoPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    private ProductoDAO dao;
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField campoNombre, campoCategoria, campoPrecio, campoDescripcion, campoStock;

    public ProductoPanel() {
        dao = new ProductoDAO();
        setLayout(new BorderLayout());

        // Tabla
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Categoría", "Precio", "Descripción", "Stock"}, 0);
        tabla = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        // Formulario superior
        JPanel form = new JPanel(new GridLayout(2, 6, 5, 5));
        campoNombre = new JTextField();
        campoCategoria = new JTextField();
        campoPrecio = new JTextField();
        campoDescripcion = new JTextField();
        campoStock = new JTextField();

        form.add(new JLabel("Nombre:")); form.add(new JLabel("Categoría:"));
        form.add(new JLabel("Precio:")); form.add(new JLabel("Descripción:"));
        form.add(new JLabel("Stock:")); form.add(new JLabel());

        form.add(campoNombre); form.add(campoCategoria);
        form.add(campoPrecio); form.add(campoDescripcion);
        form.add(campoStock); form.add(new JLabel()); // Espacio vacío

        add(form, BorderLayout.NORTH);

        // Panel de botones
        JPanel botones = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton btnAgregar = new JButton("Agregar");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");
        JButton btnResumen = new JButton("📋 Ver resumen");
        JButton btnIA = new JButton("🤖 Asistente IA");

        botones.add(btnAgregar);
        botones.add(btnActualizar);
        botones.add(btnEliminar);
        botones.add(btnResumen);
        botones.add(btnIA);

        add(botones, BorderLayout.SOUTH);

        // Eventos
        btnAgregar.addActionListener(e -> agregarProducto());
        btnActualizar.addActionListener(e -> actualizarProducto());
        btnEliminar.addActionListener(e -> eliminarProducto());

        tabla.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int fila = tabla.getSelectedRow();
                campoNombre.setText(tabla.getValueAt(fila, 1).toString());
                campoCategoria.setText(tabla.getValueAt(fila, 2).toString());
                campoPrecio.setText(tabla.getValueAt(fila, 3).toString());
                campoDescripcion.setText(tabla.getValueAt(fila, 4).toString());
                campoStock.setText(tabla.getValueAt(fila, 5).toString());
            }
        });

        btnResumen.addActionListener(e -> {
            String resumen = dao.generarResumenProductos();
            JOptionPane.showMessageDialog(this, resumen, "Resumen de Productos", JOptionPane.INFORMATION_MESSAGE);
        });

        btnIA.addActionListener(e -> {
            String resumen = dao.generarResumenProductos();
            String prompt = resumen + "\n\nDame ideas para mejorar las ventas y el inventario.";
            LlmService ia = new LlmService();
            String respuesta = ia.consultarLLM(prompt);
            JOptionPane.showMessageDialog(this, respuesta, "Asistente IA", JOptionPane.INFORMATION_MESSAGE);
        });

        cargarProductos();
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        List<ProductoOtaku> productos = dao.obtenerTodosLosProductos();
        for (ProductoOtaku p : productos) {
            modeloTabla.addRow(new Object[]{
                    p.getId(), p.getNombre(), p.getCategoria(), p.getPrecio(), p.getDescripcion(), p.getStock()
            });
        }
    }

    private void agregarProducto() {
        try {
            String nombre = campoNombre.getText();
            String categoria = campoCategoria.getText();
            double precio = Double.parseDouble(campoPrecio.getText());
            String descripcion = campoDescripcion.getText();
            int stock = Integer.parseInt(campoStock.getText());

            ProductoOtaku nuevo = new ProductoOtaku(nombre, categoria, precio, descripcion, stock);
            dao.agregarProducto(nuevo);
            cargarProductos();
            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar producto: " + e.getMessage());
        }
    }

    private void actualizarProducto() {
        try {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para actualizar.");
                return;
            }

            int id = (int) tabla.getValueAt(fila, 0);
            String nombre = campoNombre.getText();
            String categoria = campoCategoria.getText();
            double precio = Double.parseDouble(campoPrecio.getText());
            String descripcion = campoDescripcion.getText();
            int stock = Integer.parseInt(campoStock.getText());

            ProductoOtaku actualizado = new ProductoOtaku(id, nombre, categoria, precio, descripcion, stock);
            dao.actualizarProducto(actualizado);
            cargarProductos();
            limpiarCampos();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al actualizar producto: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        try {
            int fila = tabla.getSelectedRow();
            if (fila == -1) {
                JOptionPane.showMessageDialog(this, "Selecciona un producto para eliminar.");
                return;
            }
            int id = (int) tabla.getValueAt(fila, 0);
            dao.eliminarProducto(id);
            cargarProductos();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar producto: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoCategoria.setText("");
        campoPrecio.setText("");
        campoDescripcion.setText("");
        campoStock.setText("");
    }
}

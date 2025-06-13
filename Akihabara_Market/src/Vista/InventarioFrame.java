package Vista;

import Modelo.ProductoDAO;
import Modelo.ProductoOtaku;
import Modelo.LlmService;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.List;

public class InventarioFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    private ProductoDAO dao = new ProductoDAO();
    private JTable tabla;
    private DefaultTableModel modeloTabla;
    private JTextField campoNombre, campoCategoria, campoPrecio, campoDescripcion, campoStock;
    private JButton btnAgregar, btnActualizar, btnEliminar, btnIA;

    public InventarioFrame() {
        setTitle("🛒 Akihabara Market - Inventario");
        setSize(1000, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(15, 10));

        JPanel panelPrincipal = new JPanel(new BorderLayout(10, 10));
        panelPrincipal.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        panelPrincipal.setBackground(new Color(250, 250, 250));

        // FORMULARIO
        JPanel formulario = new JPanel(new GridBagLayout());
        formulario.setBorder(new TitledBorder(new LineBorder(Color.GRAY, 1, true), "Información del Producto"));
        formulario.setBackground(new Color(245, 245, 245));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoNombre = new JTextField(); campoNombre.setPreferredSize(new Dimension(200, 25));
        campoCategoria = new JTextField(); campoCategoria.setPreferredSize(new Dimension(200, 25));
        campoPrecio = new JTextField(); campoPrecio.setPreferredSize(new Dimension(200, 25));
        campoDescripcion = new JTextField(); campoDescripcion.setPreferredSize(new Dimension(200, 25));
        campoStock = new JTextField(); campoStock.setPreferredSize(new Dimension(200, 25));

        String[] labels = {"Nombre", "Categoría", "Precio", "Descripción", "Stock"};
        JTextField[] campos = {campoNombre, campoCategoria, campoPrecio, campoDescripcion, campoStock};

        for (int i = 0; i < labels.length; i++) {
            gbc.gridx = 0; gbc.gridy = i;
            formulario.add(new JLabel(labels[i]), gbc);
            gbc.gridx = 1;
            formulario.add(campos[i], gbc);
        }

        JPanel formularioWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        formularioWrapper.setBackground(new Color(245, 245, 245));
        formularioWrapper.add(formulario);

        panelPrincipal.add(formularioWrapper, BorderLayout.NORTH);

        // TABLA
        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Categoría", "Precio", "Descripción", "Stock"}, 0);
        tabla = new JTable(modeloTabla);
        tabla.setRowHeight(24);
        tabla.setFont(new Font("SansSerif", Font.PLAIN, 13));
        tabla.getTableHeader().setFont(new Font("SansSerif", Font.BOLD, 14));
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createTitledBorder("Inventario de Productos"));
        panelPrincipal.add(scroll, BorderLayout.CENTER);

        // BOTONES
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelBotones.setBackground(new Color(245, 245, 245));

        btnAgregar = new JButton("Agregar");
        btnActualizar = new JButton("Actualizar");
        btnEliminar = new JButton("Eliminar");
        btnIA = new JButton("Asistente IA");

        for (JButton b : new JButton[]{btnAgregar, btnActualizar, btnEliminar, btnIA}) {
            b.setPreferredSize(new Dimension(120, 30));
            b.setFont(new Font("SansSerif", Font.BOLD, 13));
            panelBotones.add(b);
        }

        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);
        add(panelPrincipal);

        // EVENTOS
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

        btnIA.addActionListener(e -> {
            String pregunta = JOptionPane.showInputDialog(this, "¿Qué deseas saber sobre el inventario?");
            if (pregunta != null && !pregunta.trim().isEmpty()) {
                try {
                    String contexto = dao.generarResumenProductos();
                    String prompt = contexto + "\n\nUsuario pregunta: " + pregunta;

                    LlmService ia = new LlmService();
                    String respuesta = ia.consultarLLM(prompt);

                    JTextArea area = new JTextArea(respuesta);
                    area.setLineWrap(true);
                    area.setWrapStyleWord(true);
                    area.setEditable(false);
                    JScrollPane scrollArea = new JScrollPane(area);
                    scrollArea.setPreferredSize(new Dimension(500, 300));

                    JOptionPane.showMessageDialog(this, scrollArea, "Respuesta de la IA", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(this, "Error al consultar la IA: " + ex.getMessage());
                }
            }
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
            JOptionPane.showMessageDialog(this, "Error al agregar: " + e.getMessage());
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
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + e.getMessage());
        }
    }

    private void eliminarProducto() {
        try {
            int fila = tabla.getSelectedRow();
            if (fila == -1) return;
            int id = (int) tabla.getValueAt(fila, 0);
            dao.eliminarProducto(id);
            cargarProductos();
            limpiarCampos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + e.getMessage());
        }
    }

    private void limpiarCampos() {
        campoNombre.setText("");
        campoCategoria.setText("");
        campoPrecio.setText("");
        campoDescripcion.setText("");
        campoStock.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new InventarioFrame().setVisible(true));
    }
}

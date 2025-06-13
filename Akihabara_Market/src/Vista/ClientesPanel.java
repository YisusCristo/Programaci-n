package Vista;

import Modelo.ClienteDAO;
import Modelo.ClienteOtaku;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.util.List;

public class ClientesPanel extends JPanel {
    private JTable tablaClientes;
    private DefaultTableModel modeloTabla;
    private JTextField campoNombre, campoEmail, campoTelefono, campoId;
    private ClienteDAO clienteDAO;

    public ClientesPanel() {
        this.clienteDAO = new ClienteDAO();
        setLayout(new BorderLayout());

        modeloTabla = new DefaultTableModel(new String[]{"ID", "Nombre", "Email", "Teléfono", "Fecha Registro"}, 0);
        tablaClientes = new JTable(modeloTabla);
        JScrollPane scroll = new JScrollPane(tablaClientes);
        add(scroll, BorderLayout.CENTER);

        // Nuevo diseño de formulario usando GridBagLayout
        JPanel formulario = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        campoId = new JTextField(10);
        campoNombre = new JTextField(10);
        campoEmail = new JTextField(10);
        campoTelefono = new JTextField(10);

        // Fila 1
        gbc.gridx = 0; gbc.gridy = 0; formulario.add(new JLabel("ID:"), gbc);
        gbc.gridx = 1; formulario.add(campoId, gbc);
        gbc.gridx = 2; formulario.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 3; formulario.add(campoNombre, gbc);

        // Fila 2
        gbc.gridx = 0; gbc.gridy = 1; formulario.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1; formulario.add(campoEmail, gbc);
        gbc.gridx = 2; formulario.add(new JLabel("Teléfono:"), gbc);
        gbc.gridx = 3; formulario.add(campoTelefono, gbc);

        // Fila 3: Botones
        JButton btnAgregar = new JButton("Agregar");
        JButton btnBuscar = new JButton("Buscar por ID");
        JButton btnActualizar = new JButton("Actualizar");
        JButton btnEliminar = new JButton("Eliminar");

        gbc.gridx = 0; gbc.gridy = 2; formulario.add(btnAgregar, gbc);
        gbc.gridx = 1; formulario.add(btnBuscar, gbc);
        gbc.gridx = 2; formulario.add(btnActualizar, gbc);
        gbc.gridx = 3; formulario.add(btnEliminar, gbc);

        add(formulario, BorderLayout.SOUTH);

        btnAgregar.addActionListener(e -> agregarCliente());
        btnBuscar.addActionListener(e -> buscarClientePorId());
        btnActualizar.addActionListener(e -> actualizarCliente());
        btnEliminar.addActionListener(e -> eliminarCliente());

        cargarClientes();
    }

    private void cargarClientes() {
        try {
            List<ClienteOtaku> lista = clienteDAO.obtenerTodosLosClientes();
            modeloTabla.setRowCount(0);
            for (ClienteOtaku c : lista) {
                modeloTabla.addRow(new Object[]{c.getId(), c.getNombre(), c.getEmail(), c.getTelefono(), c.getFechaRegistro()});
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar clientes: " + ex.getMessage());
        }
    }

    private void agregarCliente() {
        String nombre = campoNombre.getText();
        String email = campoEmail.getText();
        String telefono = campoTelefono.getText();
        if (nombre.isEmpty() || email.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nombre y Email son obligatorios.");
            return;
        }
        try {
            ClienteOtaku nuevo = new ClienteOtaku(nombre, email, telefono);
            clienteDAO.agregarCliente(nuevo);
            cargarClientes();
            limpiarCampos();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al agregar cliente: " + ex.getMessage());
        }
    }

    private void buscarClientePorId() {
        try {
            int id = Integer.parseInt(campoId.getText());
            ClienteOtaku c = clienteDAO.obtenerClientePorId(id);
            if (c != null) {
                campoNombre.setText(c.getNombre());
                campoEmail.setText(c.getEmail());
                campoTelefono.setText(c.getTelefono());
            } else {
                JOptionPane.showMessageDialog(this, "Cliente no encontrado.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar: " + ex.getMessage());
        }
    }

    private void actualizarCliente() {
        try {
            int id = Integer.parseInt(campoId.getText());
            String nombre = campoNombre.getText();
            String email = campoEmail.getText();
            String telefono = campoTelefono.getText();
            ClienteOtaku actualizado = new ClienteOtaku(id, nombre, email, telefono, null);
            if (clienteDAO.actualizarCliente(actualizado)) {
                JOptionPane.showMessageDialog(this, "Cliente actualizado.");
                cargarClientes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo actualizar el cliente.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar: " + ex.getMessage());
        }
    }

    private void eliminarCliente() {
        try {
            int id = Integer.parseInt(campoId.getText());
            if (clienteDAO.eliminarCliente(id)) {
                JOptionPane.showMessageDialog(this, "Cliente eliminado.");
                cargarClientes();
                limpiarCampos();
            } else {
                JOptionPane.showMessageDialog(this, "No se pudo eliminar el cliente.");
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar: " + ex.getMessage());
        }
    }

    private void limpiarCampos() {
        campoId.setText("");
        campoNombre.setText("");
        campoEmail.setText("");
        campoTelefono.setText("");
    }
}

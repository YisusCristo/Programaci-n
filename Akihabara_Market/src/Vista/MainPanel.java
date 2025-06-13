package Vista;

import com.formdev.flatlaf.FlatIntelliJLaf;
import javax.swing.*;
import java.awt.*;
import Modelo.LlmService;

public class MainPanel extends JFrame {

    public MainPanel() {
        super("Akihabara Market • Gestión");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1000, 650);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        setResizable(false);

        // ===== Encabezado con botón IA =====
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titulo = new JLabel("Akihabara Market - Sistema de Gestión", SwingConstants.LEFT);
        titulo.setFont(new Font("Segoe UI", Font.BOLD, 20));
        titulo.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));

        JButton btnIA = new JButton("🤖 IA Asistente");
        btnIA.setFocusable(false);
        btnIA.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnIA.addActionListener(e -> mostrarDialogoIA());

        topPanel.add(titulo, BorderLayout.WEST);
        topPanel.add(btnIA, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);

        // ===== Pestañas =====
        JTabbedPane pestañas = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.WRAP_TAB_LAYOUT);
        pestañas.setFont(new Font("Segoe UI", Font.PLAIN, 15));
        pestañas.addTab("🛒 Productos", new ProductoPanel());
        pestañas.addTab("👤 Clientes", new ClientesPanel());

        add(pestañas, BorderLayout.CENTER);
    }

    private void mostrarDialogoIA() {
        String pregunta = JOptionPane.showInputDialog(this, "¿En qué te puede ayudar la IA?");
        if (pregunta != null && !pregunta.trim().isEmpty()) {
            try {
                LlmService ia = new LlmService();
                String respuesta = ia.consultarLLM(pregunta);
                JOptionPane.showMessageDialog(this, respuesta, "Respuesta de la IA", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Error al consultar la IA: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            System.err.println("No se pudo aplicar FlatLaf.");
        }
        SwingUtilities.invokeLater(() -> new MainPanel().setVisible(true));
    }
}

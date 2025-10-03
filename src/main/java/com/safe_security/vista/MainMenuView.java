package com.safe_security.vista;

import com.safe_security.controlador.Controlador;
import com.safe_security.model.Contacto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.List;

/**
 * Vista del Menú Principal. Ahora muestra la lista de contactos y el botón de AGREGAR.
 */
public class MainMenuView extends JFrame {

    private final Controlador controlador; 

    public MainMenuView(Controlador controlador) {
        this.controlador = controlador;
        
        setTitle("Safe Security - Menú Principal");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("Sistema de Alerta Activo", SwingConstants.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        add(title, BorderLayout.NORTH);
        
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        JButton btnAlerta = new JButton("🚨 Gestionar Alerta");
        JButton btnContactos = new JButton("👥 Contactos y Notificación");
        JButton btnDispositivos = new JButton("📱 Vincular Dispositivo");
        JButton btnCerrar = new JButton("🚪 Cerrar Sesión");
        
        centerPanel.add(btnAlerta);
        centerPanel.add(btnContactos);
        centerPanel.add(btnDispositivos);
        centerPanel.add(btnCerrar);
        
        add(centerPanel, BorderLayout.CENTER);
        
        // --- LÓGICA DE BOTONES ---
        
        btnAlerta.addActionListener((ActionEvent e) -> {
            new AlertaView(this.controlador).setVisible(true);
        });

        // 2. CONTACTOS: Muestra la lista cargada y pregunta si desea agregar uno nuevo
        btnContactos.addActionListener((ActionEvent e) -> {
            List<Contacto> contactos = controlador.obtenerContactos();
            
            StringBuilder sb = new StringBuilder("Contactos de Emergencia (Cargados):\n\n");
            if (contactos != null && !contactos.isEmpty()) {
                for (Contacto c : contactos) {
                    sb.append("• ").append(c.getNombre()).append(": ").append(c.getTelefono()).append("\n");
                }
            } else {
                sb.append("No se encontraron contactos para su usuario.");
            }
            
            sb.append("\n-----------------------------------\n");
            sb.append("Presione 'Sí' para AGREGAR un nuevo contacto.");

            // Usamos confirm dialog para agregar el botón
            int respuesta = JOptionPane.showConfirmDialog(
                this, 
                // Aseguramos que el área de texto se vea bien
                new JScrollPane(new JTextArea(sb.toString(), 10, 30)), 
                "Gestión de Contactos", 
                JOptionPane.YES_NO_OPTION, 
                JOptionPane.PLAIN_MESSAGE
            );
            
            if (respuesta == JOptionPane.YES_OPTION) {
                // Abrir la nueva ventana de agregar contacto
                new AgregarContactoView(this.controlador).setVisible(true);
            }
        });

        btnDispositivos.addActionListener((ActionEvent e) -> {
            controlador.manejarVinculacionDispositivo();
            JOptionPane.showMessageDialog(this, "Proceso de vinculación iniciado (Revisa la consola).", "Proceso API", JOptionPane.INFORMATION_MESSAGE);
        });
        
        btnCerrar.addActionListener((ActionEvent e) -> {
            JOptionPane.showMessageDialog(this, "Sesión cerrada.");
            new LoginView(this.controlador).setVisible(true); 
            this.dispose(); 
        });
    }
}